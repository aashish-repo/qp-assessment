package com.qpassessment.service;

import com.qpassessment.model.ApiResponse;
import com.qpassessment.model.requests.AddItemRequest;
import com.qpassessment.model.requests.UpdateItemRequest;
import com.qpassessment.model.response.ItemListResponse;

/**
 * Basic crud operation on grocery items
 */


public interface GroceryService {

    ApiResponse addItem(AddItemRequest addItemRequest);

    ApiResponse deleteItem(String itemKey);

    ApiResponse updateItem(UpdateItemRequest updateItemRequest, String itemKey);

    ItemListResponse fetchItems(Integer pageNo, Integer pageSize) throws Exception;
}
