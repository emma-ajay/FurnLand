package com.project.FurnLand.Controller;

import com.project.FurnLand.Entity.Order;
import com.project.FurnLand.Entity.OrderedItem;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Security.CurrentUser;
import com.project.FurnLand.Security.UserPrincipal;
import com.project.FurnLand.Service.OrderService;
import com.project.FurnLand.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;


    @Transactional
    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/fromCart")
    public ResponseEntity<?> takeOrder(@CurrentUser UserPrincipal currentUser) {
        Long userId = currentUser.getId();
        return orderService.takeOrder(userId);
    }

    public OrderedItem getOrderedItemById(Long id) {
        return orderService.getOrderedItemById(id);

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/myOrders")
    public ResponseEntity<?> myOrders(@CurrentUser UserPrincipal currentUser) {
        Long userId = currentUser.getId();
        return userService.userOrders(userId);
    }




    @PreAuthorize("hasRole('VENDOR')")
    @Transactional
    @PostMapping(path = "/{id}/confirm")
    public  ResponseEntity<?> confirmOrder(@CurrentUser UserPrincipal currentUser, @PathVariable Long id){
        Long userId = currentUser.getId();
        return  userService.updateHasBeenConfirmed(userId,id);

    }

    @PreAuthorize("hasRole('VENDOR')")
    @Transactional
    @PostMapping(path = "/{id}/sent")
    public  ResponseEntity<?> confirmOrderSent(@CurrentUser UserPrincipal currentUser, @PathVariable Long id){
        Long userId = currentUser.getId();
        return  userService.updateHasBeenSent(userId,id);

    }
//confirm an ordered item has been delivered



    @PreAuthorize("hasRole('USER')")
    @Transactional
    @PostMapping(path = "{id}/delivered")
    public  ResponseEntity<?> confirmOrderDelivered(@CurrentUser UserPrincipal currentUser, @PathVariable Long id){
        Long userId = currentUser.getId();
        return  userService.updateHasBeenDelivered(userId,id);
}
    @PreAuthorize("hasRole('USER')")
    @PostMapping (path = "/remove/fromCart/{id}")
    public ResponseEntity<?> remove(@CurrentUser UserPrincipal currentUser, @PathVariable Long id){
        Long userId = currentUser.getId();
        return orderService.removeFromCart(userId,id);
    }


}
