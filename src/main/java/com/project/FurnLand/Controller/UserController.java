package com.project.FurnLand.Controller;

import com.project.FurnLand.DTO.Requests.AddressRequest;
import com.project.FurnLand.Entity.*;
import com.project.FurnLand.Repository.CartRepository;
import com.project.FurnLand.Repository.OrderedItemRepository;
import com.project.FurnLand.Repository.UserRepository;
import com.project.FurnLand.Security.CurrentUser;
import com.project.FurnLand.Security.UserPrincipal;
import com.project.FurnLand.Service.OrderService;
import com.project.FurnLand.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsersByRoleId(@PathVariable Long id){
        List<User> users = userRepository.getUserByRole(id);
        ResponseEntity response = ResponseEntity.ok(users);
        return response;


    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/currentUser/newAddress")
    public ResponseEntity<?> createAddress(@CurrentUser UserPrincipal currentUser,@RequestBody AddressRequest addressRequest){
        Long userId = currentUser.getId();
        Address address = modelMapper.map(addressRequest, Address.class);
        address.setUserId(userId);
        return userService.createAddress(address);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/currentUser/addressbook")
    public ResponseEntity<? > getUserAddresses(@CurrentUser UserPrincipal currentUser){
        Long userId = currentUser.getId();
        return userService.getUserAddresses(userId);
    }

//    @PreAuthorize("hasRole('USER')")
//    @DeleteMapping(path = "/currentUser/deleteAddress/{id}")
//    public ResponseEntity<?> deleteAddress(@CurrentUser UserPrincipal currentUser,@PathVariable Long id){
//        Long userId = currentUser.getSelectedItemId();
//        return userService.deleteUserAddress(id,userId);
//    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/cart")
    public Optional<Cart> getUserCart(@CurrentUser UserPrincipal currentUser){
        Long userId = currentUser.getId();
        return  userService.getUsersCart(userId);

    }

    @PreAuthorize("hasRole('VENDOR')")
    @GetMapping(path = "/myOrders")
    public  ResponseEntity<?> getAllVendorOrders(@CurrentUser UserPrincipal currentUser){
        Long userId = currentUser.getId();
        return  userService.vendorsOrders(userId);
    }












}
