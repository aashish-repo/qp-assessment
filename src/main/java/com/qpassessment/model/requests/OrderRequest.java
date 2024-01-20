package com.qpassessment.model.requests;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {

    @NotEmpty
    private String item;

    @NotNull
    private Integer quantity;

}
