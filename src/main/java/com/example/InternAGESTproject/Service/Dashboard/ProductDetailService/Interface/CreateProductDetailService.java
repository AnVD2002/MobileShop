package com.example.InternAGESTproject.Service.Dashboard.ProductDetailService.Interface;
import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductDetailRequest.CreateProductDetailRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CreateProductDetailService {

    public ResponseEntity<?> CreateProductDetail(CreateProductDetailRequest request);
}
