package com.example.InternAGESTproject.DTO.Dashboard.Request.ProductDetailRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDetailRequest {
    private Long productID;
    private List<Long> propertyDetailIDList;
    private Double quantity;
}
