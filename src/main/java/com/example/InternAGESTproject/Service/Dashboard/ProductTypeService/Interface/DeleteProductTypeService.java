package com.example.InternAGESTproject.Service.Dashboard.ProductTypeService.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DeleteProductTypeService {
    public ResponseEntity<?> deleteProductType();
}
