package com.example.InternAGESTproject.Service.Dashboard.ProductTypeService.Interface;

import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductTypeRequest.UpdateProductTypeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UpdateProductTypeService {
    public ResponseEntity<?> updateProductType(UpdateProductTypeRequest request);
}
