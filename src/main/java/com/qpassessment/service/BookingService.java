package com.qpassessment.service;

import com.qpassessment.model.ApiResponse;
import com.qpassessment.model.requests.OrderRequest;

public interface BookingService {

    /**
     * this api is to create the fresh bucket or update the existing bucket
     * user can add multiple items, As of now I have taken userId to track this
     * but ideally it should be driven from jwt token
     * @param orderRequest
     * @param userId
     * @return
     */

    ApiResponse updateOrder(OrderRequest orderRequest, Integer userId);


    /**
     * For completing or booking the order, As of taken userId in input but we can use jwt token
     * @param userId
     * @return
     */
    ApiResponse placeOrder(Integer userId);

}
