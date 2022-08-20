package com.project.FurnLand.Controller;

import com.project.FurnLand.DTO.Requests.RegisterRequest;
import com.project.FurnLand.DTO.Requests.SignInRequest;
import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.DTO.Response.JwtAuthenticationResponse;
import com.project.FurnLand.DTO.Response.Profile;
import com.project.FurnLand.Entity.*;
import com.project.FurnLand.Exceptions.AppException;
import com.project.FurnLand.Repository.CartRepository;
import com.project.FurnLand.Repository.RoleRepository;
import com.project.FurnLand.Repository.UserCartRepository;
import com.project.FurnLand.Repository.UserRepository;
import com.project.FurnLand.Security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserCartRepository userCartRepository;

    @Autowired
    CartRepository cartRepository;

    public Cart createCart (){
        Cart cart =  new Cart("active");
        cartRepository.save(cart);
        return cart;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest){

        User user = modelMapper.map(signInRequest,User.class);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String email = user.getEmail();
        String jwt =tokenProvider.generateToken(authentication);

        // get users role
        User user1 = userRepository.ByEmail(email);
        Set<Role> roleName = user1.getRoles() ;

        Profile profile = modelMapper.map(user1,Profile.class);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,email,profile,roleName));
    }

    // Register user Consumer
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<? > registerUser(@RequestBody RegisterRequest registerRequest){
//        if(userRepository.existsByEmail(registerRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        // create cart
        Cart cart = createCart();
        Long cart_id= cart.getId();

        User user = modelMapper.map(registerRequest,User.class);
         String password =passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));



        User result = userRepository.save(user);
        Long user_id = result.getId();

        UserCart userCart = new UserCart(user_id,cart_id);

        UserCart userCart1 = modelMapper.map(userCart,UserCart.class);

        UserCart result2 = userCartRepository.save(userCart1);


        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully",result.getId(),"user"));
    }

    @PostMapping("/register/vendor")
    public ResponseEntity<? > registerVendor(@RequestBody RegisterRequest registerRequest){
//        if(userRepository.existsByEmail(registerRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        User user = modelMapper.map(registerRequest,User.class);
        String password =passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleName.ROLE_VENDOR)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<? > registerAdmin(@RequestBody RegisterRequest registerRequest){
//        if(userRepository.existsByEmail(registerRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        User user = modelMapper.map(registerRequest,User.class);
        String password =passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
