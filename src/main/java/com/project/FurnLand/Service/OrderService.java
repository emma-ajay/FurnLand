package com.project.FurnLand.Service;


import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.Entity.Order;
import com.project.FurnLand.Entity.OrderedItem;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Repository.OrderRepository;
import com.project.FurnLand.Repository.OrderedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedItemRepository orderedItemRepository;



    // create a new order
    public ResponseEntity<?> createOrder(Order order){
        Order order1 =orderRepository.save(order);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/order/{id}")
                .buildAndExpand(order1.getId()).toUri();

        return  ResponseEntity.created(location).body(new ApiResponse(true,"Order Created Successfully"));
    }

    // get all orders
    public List<Order> getOrder(){
    return orderRepository.findAll();

    }

    // Assign Item to order

    public  ResponseEntity<?>  orderItem(OrderedItem orderedItem){

        try {
             orderedItemRepository.save(orderedItem);
        }
        catch (BadRequestException e){
            e.getMessage();
        }

        return  ResponseEntity.ok(new ApiResponse(true,"Item ordered Successfully"));

    }

    public Order updateOrder(Long id, Order order){
       Order order1 =  orderRepository.findById(id).orElseThrow(()-> new IllegalStateException("order with id " + id + " doesn't exist"));
        order1.setId(id);
      //  order1.setCustomerId(order1.getCustomerId());
        order1.setAddress(order1.getAddress());
        order1.setStatus(order.getStatus());

        orderRepository.save(order1);
        return order1;


    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersTOVendor(Long id ){
       return orderRepository.getOrdersToVendor(id);
    }


}
