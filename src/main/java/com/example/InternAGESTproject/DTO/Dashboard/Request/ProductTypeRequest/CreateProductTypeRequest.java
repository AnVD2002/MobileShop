package com.example.InternAGESTproject.DTO.Dashboard.Request.ProductTypeRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductTypeRequest {
    private String name;
    private String description;
    private Long categoryID;
}
