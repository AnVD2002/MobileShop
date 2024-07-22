package com.example.InternAGESTproject.Service.Dashboard.CategoryService.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DeleteCategoryService {
    public ResponseEntity<?> deleteCategory();
}
