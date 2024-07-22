package com.example.InternAGESTproject.Service.Dashboard.CategoryService.Interface;

import com.example.InternAGESTproject.DTO.Dashboard.Request.CategoryRequest.CreateCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CreateCategoryService {
    public ResponseEntity<?> createCategory(CreateCategoryRequest request);
}
