package com.qpassessment.model.requests;

import com.qpassessment.annotation.EnumValidation;
import com.qpassessment.constants.ItemType;
import com.qpassessment.constants.ValidationErrors;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddItemRequest {

    @NotEmpty(message = ValidationErrors.ITEM_KEY_NULL)
    private String itemKey;

    @NotEmpty(message = ValidationErrors.ITEM_NAME_NULL)
    private String name;

    private String description;

    @EnumValidation(enumClass = ItemType.class, message = ValidationErrors.ITEM_TYPE_NOT_SUPPORTED)
    private String itemType;

    @NotNull
    private Double price;

    @NotNull
    private Long initialStock;

}
