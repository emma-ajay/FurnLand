package com.project.FurnLand.Service;

import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.Entity.Order;
import com.project.FurnLand.Entity.OrderedItem;
import com.project.FurnLand.Entity.SelectedItem;
import com.project.FurnLand.Entity.UserCart;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Repository.OrderRepository;
import com.project.FurnLand.Repository.OrderedItemRepository;
import com.project.FurnLand.Repository.SelectedItemRepository;
import com.project.FurnLand.Repository.UserCartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserCartRepository userCartRepository;

    @Autowired
    SelectedItemRepository selectedItemRepository;

    @Autowired
    OrderedItemRepository orderedItemRepository;

    @Transactional
    public ResponseEntity<? > takeOrder(Long userId){
        UserCart userCart = userCartRepository.selectUserCart(userId);


        Long cartId = userCart.getCartId();

        List<SelectedItem> itemsInCart= selectedItemRepository.itemsInCart(cartId);

        int cartSize = itemsInCart.size();

        if(cartSize ==0){
            throw new BadRequestException("no Item in cart");
        }

        Order order = new Order(
                0L,
                Boolean.FALSE,
                userId
        );

        Order result = orderRepository.save(order);

        Long orderId= result.getId();

        List<OrderedItem> orderedItemList =new ArrayList<>();

        for (int i = 0 ; i< cartSize; i++){
            SelectedItem selectedItem = itemsInCart.get(i);

            OrderedItem orderedItem = new OrderedItem();

            orderedItem = modelMapper.map(selectedItem,OrderedItem.class);

            orderedItem.setOrderId(orderId);

            orderedItem.setHasBeenSent(Boolean.FALSE);
            orderedItem.setHasBeenConfirmed(Boolean.FALSE);
            orderedItem.setHasBeenDelivered(Boolean.FALSE);

            orderedItemList.add(i,orderedItem);
            try{
                selectedItemRepository.deleteById(selectedItem.getSelectedItemId());
            }
            catch (Exception ex){
                ex.getMessage();
            }
    }
        List<OrderedItem> result2 = orderedItemRepository.saveAll(orderedItemList);
       return ResponseEntity.ok(new ApiResponse(true,"Order successfully placed",orderId,"Order"));

    }

    public ResponseEntity<?> myOrders(Long userId){
        List<Order> orders = orderRepository.getUsersOrders(userId);
        return ResponseEntity.ok(orders);
    }

    public OrderedItem getOrderedItemById(Long id) {
        OrderedItem orderedItem = orderedItemRepository.findOrderedItemById(id);
        if(orderedItem == null){
            throw new BadRequestException("Ordered Item doesn't exist");
        }
        return  orderedItem;
    }

    // remove item from cart
    public ResponseEntity<?> removeFromCart(Long userId , Long selectedItemId){

        SelectedItem selectedItem = selectedItemRepository.findById(selectedItemId).orElseThrow(()-> new BadRequestException("Item not in cart"));
        Long user =selectedItem.getUserId();

        if(!Objects.equals(user,userId)) throw  new BadRequestException("not your cart ");

        try {
           selectedItemRepository.deleteById(selectedItem.getSelectedItemId());
       }
       catch (Exception ex){
           ex.getMessage();
       }
       return  ResponseEntity.ok(new ApiResponse(true,"removed from cart",selectedItemId,"selected item in cart"));
    }
}
