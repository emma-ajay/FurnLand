package com.project.FurnLand.Service;

import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.Entity.Address;
import com.project.FurnLand.Entity.Cart;
import com.project.FurnLand.Entity.User;
import com.project.FurnLand.Entity.UserCart;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Repository.AddressRepository;
import com.project.FurnLand.Repository.CartRepository;
import com.project.FurnLand.Repository.UserCartRepository;
import com.project.FurnLand.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserCartRepository userCartRepository;

    @Autowired
    CartRepository cartRepository;


    public ResponseEntity<?> getAllUserByRoleId(Long id){
        List<User> users = userRepository.getUserByRole(id);
        ResponseEntity response = ResponseEntity.ok(users);
        return response;
    }
    public ResponseEntity<?> createAddress( Address address){

        Address address1 = addressRepository.save(address);
        ResponseEntity response = ResponseEntity.ok(new ApiResponse(true,"new address added",address1.getId(),"address"));
        return response;
    }

    public ResponseEntity<?> getUserAddresses(Long userId ){
        List<Address> addresses = addressRepository.getAddressByUserId(userId);
        ResponseEntity response = ResponseEntity.ok(addresses);
        return response;
    }

    public ResponseEntity<?> deleteUserAddress(Long addressId,Long userId){

        Address address = addressRepository.findById(addressId).orElseThrow(()-> new BadRequestException("address doesn't exist"));
        Long user = address.getUserId();
        if(userId != user){
            throw  new BadRequestException("This address doesn't belong to you");
        }
        try{
            addressRepository.deleteById(addressId);
        }
        catch (BadRequestException ex){
            ex.getMessage();
        }
        return ResponseEntity.ok("address deleted");
    }


    // get users cart

    public  Optional<Cart> getUsersCart( Long userId ){
        UserCart userCart = userCartRepository.selectUserCart(userId);
        Long cartId = userCart.getCartId();
        Optional<Cart> cart = cartRepository.findById(cartId);

       // ResponseEntity<?> response = ResponseEntity.ok(cart);

        return  cart;

    }

}
