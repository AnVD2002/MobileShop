package com.example.InternAGESTproject.DTO.Dashboard.Request.ProductTypeRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductTypeRequest {
    private Long productTypeID;
    private Boolean status;
    private String name;
    private String description;
    private Long categoryID;
}
