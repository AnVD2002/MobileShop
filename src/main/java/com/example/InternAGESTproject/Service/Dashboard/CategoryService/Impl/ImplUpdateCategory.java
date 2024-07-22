package com.example.InternAGESTproject.Service.Dashboard.CategoryService.Impl;

import com.example.InternAGESTproject.DTO.Dashboard.Request.CategoryRequest.UpdateCategoryRequest;
import com.example.InternAGESTproject.Entity.Categories;
import com.example.InternAGESTproject.Repository.CategoriesRepository;
import com.example.InternAGESTproject.Service.Dashboard.CategoryService.Interface.UpdateCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
@RequiredArgsConstructor
public class ImplUpdateCategory implements UpdateCategoryService {

    private final CategoriesRepository categoriesRepository;

    @Override
    public ResponseEntity<?> updateCategory(UpdateCategoryRequest request) {

        //check null cate
        if(request.getCategoryID()==null){
            return new ResponseEntity<>("Không có danh mục này ", HttpStatus.NO_CONTENT);
        }
        Optional<Categories> category = categoriesRepository.findById(request.getCategoryID());
        //check null cate
        if(category.isEmpty()){
            return new ResponseEntity<>("Không tồn tại danh mục này ", HttpStatus.NOT_FOUND);
        }

        String name = request.getName();
        //check null name
        if (name != null && !name.isEmpty()) {
            category.get().setName(name);
        }

        Boolean status = request.getStatus();
        //check null status
        if (status != null) {
            category.get().setStatus(status);
        }
        categoriesRepository.save(category.get());

        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
    }
}
