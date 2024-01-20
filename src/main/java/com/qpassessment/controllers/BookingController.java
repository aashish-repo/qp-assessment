package com.qpassessment.controllers;


import com.qpassessment.model.ApiResponse;
import com.qpassessment.model.requests.OrderRequest;
import com.qpassessment.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/order")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/updateOrder/{userId}")
    @PreAuthorize("hasRole('ADMIN','USER')")
    public ResponseEntity<ApiResponse> addItemInOrder(@Valid @RequestBody OrderRequest orderRequest, @NotNull @PathVariable Integer userId){
        return new ResponseEntity<>(bookingService.updateOrder(orderRequest,userId), HttpStatus.OK);
    }

    @PostMapping("/placeOrder/{userId}")
    @PreAuthorize("hasRole('ADMIN','USER')")
    public ResponseEntity<ApiResponse> placeOrder(@NotNull @PathVariable Integer userId){
        return new ResponseEntity<>(bookingService.placeOrder(userId), HttpStatus.OK);
    }

}
