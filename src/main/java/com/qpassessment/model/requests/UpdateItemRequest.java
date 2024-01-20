package com.qpassessment.model.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemRequest {

    private String name;

    private Double price;

    private Long updateStock;

    private String description;
}
