package com.project.FurnLand.Controller;

import com.project.FurnLand.DTO.Requests.RegisterRequest;
import com.project.FurnLand.DTO.Requests.SignInRequest;
import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.DTO.Response.JwtAuthenticationResponse;
import com.project.FurnLand.Entity.Role;
import com.project.FurnLand.Entity.RoleName;
import com.project.FurnLand.Entity.User;
import com.project.FurnLand.Exceptions.AppException;
import com.project.FurnLand.Repository.RoleRepository;
import com.project.FurnLand.Repository.UserRepository;
import com.project.FurnLand.Security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.net.URI;
import java.util.Collections;

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

        String jwt =tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    // Register user Consumer
    @PostMapping("/register")
    public ResponseEntity<? > registerUser(@RequestBody RegisterRequest registerRequest){
//        if(userRepository.existsByEmail(registerRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        User user = modelMapper.map(registerRequest,User.class);
         String password =passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(password);

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
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