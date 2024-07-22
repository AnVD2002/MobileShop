package com.example.InternAGESTproject.Service.Dashboard.ProductService.Interface;

import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductRequest.CreateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CreateProductService {
    public ResponseEntity<?> createProduct(CreateProductRequest request);
}
