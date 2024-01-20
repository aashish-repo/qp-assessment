package com.qpassessment.service.impl;

import com.qpassessment.constants.ItemType;
import com.qpassessment.entity.GroceryItem;
import com.qpassessment.model.ApiResponse;
import com.qpassessment.model.requests.AddItemRequest;
import com.qpassessment.model.requests.UpdateItemRequest;
import com.qpassessment.model.response.ItemList;
import com.qpassessment.model.response.ItemListResponse;
import com.qpassessment.repository.GroceryRepository;
import com.qpassessment.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public ApiResponse addItem(AddItemRequest addItemRequest) {
        try {
            groceryRepository.save(buildGroceryItemFromRequest(addItemRequest));
        }
        catch (Exception e){

         return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"Failure");

        }
        return  new ApiResponse(HttpStatus.CREATED.toString(), "Success");
    }

    @Override
    public ApiResponse deleteItem(String itemKey) {
        try {
            Optional<GroceryItem> groceryItemOptional = groceryRepository.findByItemKey(itemKey);
            if (groceryItemOptional.isEmpty()) {
                new ApiResponse(HttpStatus.NOT_FOUND.toString(), "Failure");
            }
            groceryRepository.delete(groceryItemOptional.get());
        }
        catch (Exception e){
            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"Failure");
        }
        return new ApiResponse(HttpStatus.OK.toString(),"Success");
    }

    @Override
    public ApiResponse updateItem(UpdateItemRequest updateItemRequest, String itemKey) {
        try {
            Optional<GroceryItem> groceryItemOptional = groceryRepository.findByItemKey(itemKey);
            if (groceryItemOptional.isEmpty()) {
                new ApiResponse(HttpStatus.NOT_FOUND.toString(), "Failure");
            }
            updateGroceryItemFromRequest(groceryItemOptional.get(), updateItemRequest);
            groceryRepository.save(groceryItemOptional.get());
        }
        catch (Exception e){
            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"Failure");
        }
        return new ApiResponse(HttpStatus.OK.toString(),"Success");
    }

    @Override
    public ItemListResponse fetchItems(Integer pageNo, Integer pageSize) throws Exception {
        if(pageNo < 1){
            throw new Exception("PageNo cannot be less than 1");
        }

        if(pageSize < 1){
            throw new Exception("PageSize cannot be less than 1");
        }
        if (pageSize > 100) {
            pageSize = 100;
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<GroceryItem> result;
        try {
            result = groceryRepository.findAll(pageable);
        }
        catch (Exception e){
            throw e;
        }

        List<ItemList> itemListList = buildListResponse(result);

        return new ItemListResponse(itemListList,result.getNumber()+1, result.getTotalPages(), result.getTotalElements());
    }

    private  List<ItemList> buildListResponse(Page<GroceryItem> items){
        List<ItemList> itemList = new ArrayList<>();
        for(GroceryItem groceryItem : items){
            ItemList list = new ItemList();
            list.setItemKey(groceryItem.getItemKey());
            list.setDescription(groceryItem.getDescription());
            list.setName(groceryItem.getName());
            list.setPrice(groceryItem.getPrice());
            list.setCurrentStock(groceryItem.getCurrentStock());
            list.setGroceryType(groceryItem.getGroceryType());
            itemList.add(list);
        }
        return itemList;
    }



    private GroceryItem buildGroceryItemFromRequest(AddItemRequest addItemRequest){
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setItemKey(addItemRequest.getItemKey());
        groceryItem.setGroceryType(ItemType.valueOf(addItemRequest.getItemType()));
        groceryItem.setName(addItemRequest.getName());
        groceryItem.setDescription(addItemRequest.getDescription());
        groceryItem.setPrice(addItemRequest.getPrice());
        groceryItem.setCurrentStock(addItemRequest.getInitialStock());
        return groceryItem;
    }

    private void updateGroceryItemFromRequest(GroceryItem groceryItem, UpdateItemRequest updateItemRequest){
        if(updateItemRequest.getPrice() !=null){
            groceryItem.setPrice(updateItemRequest.getPrice());
        }
        if(updateItemRequest.getUpdateStock() !=null){
            groceryItem.setCurrentStock(groceryItem.getCurrentStock() + updateItemRequest.getUpdateStock());
        }
        if(updateItemRequest.getName() !=null){
            groceryItem.setName(updateItemRequest.getName());
        }
        if(updateItemRequest.getDescription()!=null){
            groceryItem.setDescription(updateItemRequest.getDescription());
        }
    }

}
