package com.qpassessment.controllers;

import com.qpassessment.model.ApiResponse;
import com.qpassessment.model.requests.AddItemRequest;
import com.qpassessment.model.requests.UpdateItemRequest;
import com.qpassessment.model.response.ItemListResponse;
import com.qpassessment.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/grocery")
public class GroceryController {

    @Autowired
    private GroceryService groceryService;


    @PostMapping("/item")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> addItem(@Valid @RequestBody AddItemRequest addItemRequest){

        return new ResponseEntity<>(groceryService.addItem(addItemRequest), HttpStatus.OK);

    }

    @DeleteMapping("/item/{itemKey}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteItem(@PathVariable @NotEmpty String itemKey){
        return new ResponseEntity<>(groceryService.deleteItem(itemKey), HttpStatus.OK);
    }


    @PutMapping("/item/{itemKey}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateItem(@PathVariable @NotEmpty String itemKey, @Valid @RequestBody UpdateItemRequest updateItemRequest){
        return new ResponseEntity<>(groceryService.updateItem(updateItemRequest,itemKey), HttpStatus.OK);
    }

    @GetMapping("/list/{pageNo}/{pageSize}")
    @PreAuthorize("hasRole('ADMIN','USER')")
    public ResponseEntity<ItemListResponse> fetchGroceryItems(@PathVariable @NotNull Integer pageNo, @PathVariable @NotNull Integer pageSize) throws Exception {
        return new ResponseEntity<>(groceryService.fetchItems(pageNo,pageSize), HttpStatus.OK);
    }


}
