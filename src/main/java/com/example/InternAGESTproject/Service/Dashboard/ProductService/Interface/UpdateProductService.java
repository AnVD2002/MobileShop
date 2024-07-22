package com.example.InternAGESTproject.Service.Dashboard.ProductService.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UpdateProductService {
    public ResponseEntity<?> updateProduct();
}
