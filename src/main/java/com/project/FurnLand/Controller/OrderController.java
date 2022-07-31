package com.project.FurnLand.Controller;

import com.project.FurnLand.Security.CurrentUser;
import com.project.FurnLand.Security.UserPrincipal;
import com.project.FurnLand.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @Transactional
    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/fromCart")
    public ResponseEntity<? > takeOrder(@CurrentUser UserPrincipal currentUser){
       Long userId =  currentUser.getId();
       return orderService.takeOrder(userId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path ="/myOrders")
    public ResponseEntity<?> myOrders(@CurrentUser UserPrincipal currentUser){
        Long userId =  currentUser.getId();
        return orderService.myOrders(userId);
    }
}
