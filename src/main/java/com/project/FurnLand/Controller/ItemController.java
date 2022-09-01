package com.project.FurnLand.Controller;

import com.project.FurnLand.Config.AppConstants;
import com.project.FurnLand.DTO.Requests.ItemCreationRequest;
import com.project.FurnLand.DTO.Requests.ItemUpdateRequest;
import com.project.FurnLand.DTO.Response.PagedResponse;
import com.project.FurnLand.Entity.Item;
import com.project.FurnLand.Entity.SelectedItem;
import com.project.FurnLand.Entity.UserCart;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Repository.SelectedItemRepository;
import com.project.FurnLand.Repository.UserCartRepository;
import com.project.FurnLand.Security.CurrentUser;
import com.project.FurnLand.Security.UserPrincipal;
import com.project.FurnLand.Service.AzureBlobAdapter;
import com.project.FurnLand.Service.ItemService;
import com.project.FurnLand.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    AzureBlobAdapter azureAdapter;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SelectedItemRepository selectedItemRepository;

    @Autowired
    UserCartRepository userCartRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/items")
    public List <Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping(path = "/search/{keyword}")
    public List <Item> searchItems(@PathVariable String keyword){
        return itemService.searchItems(keyword);
    }

    @GetMapping(path ="/{page}/{size}/")
    public PagedResponse<Item> allItems(@PathVariable int page,
                                        @PathVariable int size, @RequestParam(value = "q") String q){
        return  itemService.search(page,size,q);
    }

    @GetMapping("/{page}/{size}")
    public PagedResponse<Item> allItems(@PathVariable int page,
                                        @PathVariable int size){
        return itemService.allItems(page,size);
    }


    @GetMapping(path = "/items/{id}")
        public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/vendor/{id}")
    public  ResponseEntity<?> itemByVendor(@PathVariable Long id){
        return itemService.getItemsByVendor(id);
    }

    @PreAuthorize("hasRole('VENDOR')")
    @PostMapping (path = "/itemCreation")
    public ResponseEntity<?> createItem(@CurrentUser UserPrincipal userPrincipal, @RequestBody ItemCreationRequest itemCreationRequest){
        Item item = modelMapper.map(itemCreationRequest,Item.class);
        item.setVendorId(userPrincipal.getId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(item.getId()).toUri();

        ResponseEntity<?> response = itemService.createItem(item,location);
        return  response;
    }

    @PreAuthorize("hasRole('VENDOR')")
    @PutMapping(path = "/updateItem/{id}")
    public ResponseEntity<?> updateItem( @CurrentUser UserPrincipal currentUser ,@PathVariable Long id, @RequestBody ItemUpdateRequest itemUpdateRequest){
        Long userId = currentUser.getId();
        Item item = modelMapper.map(itemUpdateRequest,Item.class);
        ResponseEntity<?> response =  itemService.updateItem(id,item,userId);
        return  response;

    }

    @PreAuthorize("hasRole('VENDOR')")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteItem(@CurrentUser UserPrincipal currentUser, @PathVariable  Long id ){
        Long userId = currentUser.getId();
        ResponseEntity response = itemService.deleteItemById(id,userId);
        return response;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping (path = "/itemSelect/{id}")
    public SelectedItem selectItem(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long id){
        Item item1 = getItemById(id);
        UserCart userCart = userCartRepository.selectUserCart(userPrincipal.getId());
        Long cartId = userCart.getCartId();
        SelectedItem selectedItem = modelMapper.map(item1,SelectedItem.class);
        selectedItem.setUserId(userPrincipal.getId());
        selectedItem.setCartId(cartId);
        selectedItem.setCount(1L);

       selectedItemRepository.save(selectedItem);
        return selectedItem;
    }

    @PostMapping(path = "/upload" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Item upload(@RequestPart(value = "itemPicture") MultipartFile itemPicture,@RequestParam(name = "itemId") Long itemId)  {
        if(Objects.isNull(itemPicture)) throw new BadRequestException("Include a picture");
        String name = azureAdapter.upload(itemPicture, "Product-Image");
        Item item = itemService.updateItemImage(itemId,name);
        return item;
    }




}



