package com.project.FurnLand.Controller;


import com.project.FurnLand.DTO.Requests.OrderRequest;
import com.project.FurnLand.DTO.Requests.OrderedItemRequest;
import com.project.FurnLand.DTO.Requests.UpdateOrderRequest;
import com.project.FurnLand.Entity.Order;
import com.project.FurnLand.Entity.OrderedItem;
import com.project.FurnLand.Security.CurrentUser;
import com.project.FurnLand.Security.UserPrincipal;
import com.project.FurnLand.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/newOrder")
    public ResponseEntity<?> createOrder(@CurrentUser UserPrincipal currentUser, @RequestBody OrderRequest orderRequest){
        Long userId = currentUser.getId();
        Order order = modelMapper.map(orderRequest,Order.class);
       //  order.setCustomerId(userId);
        order.setStatus("Not delivered");
        ResponseEntity<?>  response = orderService.createOrder(order);
        return response;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/newOrder/Item")
    public ResponseEntity<?> orderItem(@RequestBody OrderedItemRequest orderedItemRequest){

        OrderedItem orderedItem = new OrderedItem( orderedItemRequest.getOrderId()  , orderedItemRequest.getItemId());
      //  OrderedItem orderedItem = modelMapper.map(orderedItemRequest,OrderedItem.class);
        ResponseEntity response = orderService.orderItem(orderedItem);
        return response;
   }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/all")
    public List<Order> getAllOrders (){
        return  orderService.getAllOrders();
    }
    @PreAuthorize("hasRole('VENDOR')")
    @GetMapping(path = "/vendor/{id}")
    public List<Order> getOrderTOVendor(Long id){
        return orderService.getOrdersTOVendor(id);
    }

    @PreAuthorize("hasRole('VENDOR')")
    @PutMapping(path = "/{id}/updateOrderStatus")
    public Order updateOrder(@PathVariable Long id, @RequestBody UpdateOrderRequest updateOrderRequest){
        Order order = modelMapper.map(updateOrderRequest, Order.class);
        Order order1 =orderService.updateOrder(id,order);
        return order1;

    }



}
