package com.qpassessment.model.response;

import com.qpassessment.constants.ItemType;
import lombok.Data;

@Data
public class ItemList {

    private String itemKey;

    private String name;

    private String description;

    private ItemType groceryType;

    private Double price;

    private Long currentStock;

}
