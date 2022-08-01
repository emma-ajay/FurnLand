package com.project.FurnLand.Service;

import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.Entity.*;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    OrderedItemRepository orderedItemRepository;


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


    // Get vendors Ordered Items
    public ResponseEntity<?> vendorsOrders(Long userId){
        List <OrderedItem> orderedItemList = orderedItemRepository.getAllVendorOrders(userId);
        return ResponseEntity.ok(orderedItemList);

    }

    // update an ordered item to confirmed

    @Transactional
    public  ResponseEntity <?> updateHasBeenConfirmed(Long userId , Long orderedItemId ){

        OrderedItem orderedItem = orderedItemRepository.findById(orderedItemId).orElseThrow(()-> new IllegalStateException("Item wasn't ordered"));
        Long vendorId = orderedItem.getVendorId();

        if(userId != vendorId){
            throw  new BadRequestException("Illegal ");
        }

        orderedItemRepository.confirmOrderedItem(orderedItemId);
        return ResponseEntity.ok(new ApiResponse(true,"ordered Item confirmed ",orderedItemId,"Ordered Item"));

    }

    // update an ordered item to sent

    @Transactional
    public  ResponseEntity <?> updateHasBeenSent(Long userId , Long orderedItemId ){

        OrderedItem orderedItem = orderedItemRepository.findById(orderedItemId).orElseThrow(()-> new IllegalStateException("Item wasn't ordered"));
        Long vendorId = orderedItem.getVendorId();

        if(userId != vendorId){
            throw  new BadRequestException("Illegal ");
        }

        orderedItemRepository.confirmSentOrderedItem(orderedItemId);
        return ResponseEntity.ok(new ApiResponse(true,"ordered Item sent ",orderedItemId,"Ordered Item"));

    }

    // update an ordered item to delivered

    @Transactional
    public  ResponseEntity <?> updateHasBeenDelivered(Long userId , Long orderedItemId ){

        OrderedItem orderedItem = orderedItemRepository.findById(orderedItemId).orElseThrow(()-> new IllegalStateException("Item wasn't ordered"));
        Long userId1 = orderedItem.getUserId();

        if(userId != userId1){
            throw  new BadRequestException("Illegal ");
        }

        orderedItemRepository.confirmDeliveredOrderedItem(orderedItemId);
        return ResponseEntity.ok(new ApiResponse(true,"ordered Item delivered ",orderedItemId,"Ordered Item"));

    }
}
