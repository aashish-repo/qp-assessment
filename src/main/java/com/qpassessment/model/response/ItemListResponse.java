package com.qpassessment.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ItemListResponse {

    List<ItemList> itemListList;
    private Integer pageNo;
    private Integer totalPages;
    private Long totalRecordCount;

}
