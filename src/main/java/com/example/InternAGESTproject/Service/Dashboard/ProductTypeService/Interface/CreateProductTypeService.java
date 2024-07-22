package com.example.InternAGESTproject.Service.Dashboard.ProductTypeService.Interface;


import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductTypeRequest.CreateProductTypeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CreateProductTypeService {
    public ResponseEntity<?> createProductType(CreateProductTypeRequest request);
}
