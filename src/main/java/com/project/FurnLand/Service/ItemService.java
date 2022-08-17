package com.project.FurnLand.Service;


import com.project.FurnLand.Config.AppConstants;
import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.DTO.Response.PagedResponse;
import com.project.FurnLand.Entity.Item;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    // get all items
    public List <Item> getAllItems(){
        return  itemRepository.findAll();

    }

    public PagedResponse<Item> allItems(int page, int size){
        validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<Item> items =itemRepository.findAllItems(pageable);
        if(items.getTotalElements() == 0){
            return new PagedResponse<>(Collections.emptyList(),items.getNumber(),
                    items.getSize(),items.getTotalElements(),items.getTotalPages(),items.isLast());
        }



        return new PagedResponse<>(items.toList(),items.getNumber(),
                items.getSize(),items.getTotalElements(),items.getTotalPages(),items.isLast());
    }

    //get a particular Item
    public Item getItemById(Long id){
        return itemRepository.findItemById(id);
    }

    // get all items by a vendor
    public ResponseEntity<?> getItemsByVendor(Long userID){
        List<Item> itemList = itemRepository.itemsByVendor(userID);
        ResponseEntity response = ResponseEntity.ok(itemList);
        return response;
    }


    // Create a new Item
    public ResponseEntity<?> createItem(Item item, URI location){
        Item item1 = itemRepository.save(item);
        ResponseEntity<?> response = ResponseEntity.created(location).body(new ApiResponse(true,"Item Created successfully ",item1.getId(),"Item"));
        return response;

    }

    // Update an Item

    public ResponseEntity<?> updateItem(Long id, Item item, Long userId){
      Item item1 =itemRepository.findById(id).orElseThrow(() -> new IllegalStateException("Item with id " + id + "doesn't exist"));

    if (item1.getVendorId()!= userId){
        throw new BadRequestException("You can't update an item that doesn't belong to you ");
    }
    item1.setVendorId(userId);
     item1.setItemType(item.getItemType());
     item1.setItemName(item.getItemName());
     item1.setItemDescription(item.getItemDescription());
     item1.setItemCategory(item.getItemCategory());
     item1.setImageUrl(item.getImageUrl());
     item1.setDeliveryEstimation(item.getDeliveryEstimation());
     item1.setItemPrice(item.getItemPrice());

     itemRepository.save(item1);
     ResponseEntity response = ResponseEntity.ok(new ApiResponse(true,"Item updated",item1.getId(),"Item"));
     return response;
    }


    // Delete an Item
    // Only the vendor that created an item can delete it
    public ResponseEntity deleteItemById(Long id, Long userId){
        Item item = itemRepository.findById(id).orElseThrow(()-> new IllegalStateException("Item with Id doesn't exist"));
        if (item.getVendorId() != userId ){
            throw new BadRequestException("You can't delete an item you didn't create");
        }

        try {
            itemRepository.deleteById(id);
        }
       catch (BadRequestException ex){
            ex.getCause();
       }
        ResponseEntity response = ResponseEntity.ok(new ApiResponse(true,"Item Deleted",item.getId(),"Item,"));
        return response;
    }

    public List<Item> searchItems(String keyword){
        return itemRepository.search(keyword);
    }

    // validate page number and size
    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

}
