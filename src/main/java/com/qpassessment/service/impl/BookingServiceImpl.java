package com.qpassessment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpassessment.constants.OrderStatus;
import com.qpassessment.entity.GroceryItem;
import com.qpassessment.entity.Order;
import com.qpassessment.model.ApiResponse;
import com.qpassessment.model.requests.OrderRequest;
import com.qpassessment.repository.GroceryRepository;
import com.qpassessment.repository.OrderRepository;
import com.qpassessment.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryRepository groceryRepository;
    @Override
    public ApiResponse updateOrder(OrderRequest orderRequest, Integer userId) {

        if(!isItemInStock(orderRequest)){
            throw new RuntimeException("Item is sold out");
        }
        Order order = orderRepository.findByUserIdAndStatus(userId,OrderStatus.IN_PROGRESS.name());
        if(order ==null){
            orderRepository.save(buildFreshOrder(orderRequest,userId));
        }
        else{
           String itemWithQuantity = order.getItemWithQuantity();
           List<OrderRequest> orderRequestList = new ArrayList<>();
           orderRequestList.add(orderRequest);
           orderRequestList.add(new ObjectMapper().convertValue(itemWithQuantity,OrderRequest.class));
            try {
                order.setItemWithQuantity(new ObjectMapper().writeValueAsString(orderRequestList));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            orderRepository.save(order);
        }

        return new ApiResponse(HttpStatus.CREATED.toString(), "Success");
    }

    @Override
    public ApiResponse placeOrder(Integer userId) {
        Order order = orderRepository.findByUserIdAndStatus(userId,OrderStatus.IN_PROGRESS.name());
        if(order==null){
            throw new RuntimeException("There is no pending order");
        }

        order.setOrderStatus(OrderStatus.SUCCESS);
        orderRepository.save(order);

        return new ApiResponse(HttpStatus.OK.toString(),"Success");
    }

    private Order buildFreshOrder(OrderRequest orderRequest, Integer userId) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order.setUserId(userId);
        try {
            order.setItemWithQuantity(new ObjectMapper().writeValueAsString(orderRequest));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return  order;
    }

    private boolean isItemInStock(OrderRequest orderRequest){
        Optional<GroceryItem> groceryItem =groceryRepository.findByItemKey(orderRequest.getItem());
        if(groceryItem.isEmpty()){
            throw new InternalError("Item is not found in the grocery");
        }
        if(groceryItem.get().getCurrentStock() > orderRequest.getQuantity()){
            return true;
        }
        return false;

    }

}
