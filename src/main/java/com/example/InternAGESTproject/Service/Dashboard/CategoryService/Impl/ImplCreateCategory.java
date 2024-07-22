package com.example.InternAGESTproject.Service.Dashboard.CategoryService.Impl;

import com.example.InternAGESTproject.DTO.Dashboard.Request.CategoryRequest.CreateCategoryRequest;
import com.example.InternAGESTproject.Entity.Categories;
import com.example.InternAGESTproject.Repository.CategoriesRepository;
import com.example.InternAGESTproject.Service.Dashboard.CategoryService.Interface.CreateCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
@RequiredArgsConstructor
public class ImplCreateCategory implements CreateCategoryService {

    private final CategoriesRepository categoriesRepository;

    @Override
    public ResponseEntity<?> createCategory(CreateCategoryRequest request) {
        if (request.getName().isEmpty()){
            return new ResponseEntity<>("vui lòng nhập tên", HttpStatus.NO_CONTENT);
        }
        Optional<Categories> category = categoriesRepository.findByName(request.getName());
        if (category.isPresent()){
            return new ResponseEntity<>("Đã tồn tại danh mục", HttpStatus.NOT_FOUND);
        }
        Categories newCategory = new Categories();
        newCategory.setName(request.getName());
        newCategory.setCreateAt(LocalDate.now());
        categoriesRepository.save(newCategory);
        return new ResponseEntity<>("Thêm thành công "+ request.getName(), HttpStatus.CREATED);
    }
}
