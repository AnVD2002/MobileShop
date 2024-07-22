package com.example.InternAGESTproject.DTO.Dashboard.Request.ProductRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String productName;
    private String description;
    private Long productTypeID;
    private LocalDate createAt;
    private LocalDate updateAt;
    private Double price;
    private Boolean status;
    private String screen;
    private String os;
    private String camera;
    private String cameraSelfie;
    private String cpu;
    private String ram;
    private String rom;
    private String pin;
    private String design;
    private List<Long> propertyIDs;
}
