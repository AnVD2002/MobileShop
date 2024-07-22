package com.example.InternAGESTproject.Service.Dashboard.ProductTypeService.Impl;

import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductTypeRequest.CreateProductTypeRequest;
import com.example.InternAGESTproject.Entity.Categories;
import com.example.InternAGESTproject.Entity.ProductTypes;
import com.example.InternAGESTproject.Repository.CategoriesRepository;
import com.example.InternAGESTproject.Repository.ProductTypesRepository;
import com.example.InternAGESTproject.Service.Dashboard.ProductTypeService.Interface.CreateProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
@RequiredArgsConstructor
public class ImplCreateProductType implements CreateProductTypeService {

    private final ProductTypesRepository productTypesRepository;

    private final CategoriesRepository categoriesRepository;

    @Override
    public ResponseEntity<?> createProductType(CreateProductTypeRequest request) {
        if (request.getName().isEmpty()){
            return new ResponseEntity<>("Vui lòng nhập tên", HttpStatus.NO_CONTENT);
        }
        Optional<ProductTypes> productType = productTypesRepository.findByName(request.getName());
        if (productType.isPresent()){
            return new ResponseEntity<>("Đã tồn tại loại sản phẩm này", HttpStatus.CONFLICT);
        }

        Optional<Categories> category = categoriesRepository.findById(request.getCategoryID());
        if (category.isEmpty()){
            return new ResponseEntity<>("Chưa có danh mục này" , HttpStatus.NOT_FOUND);
        }

        ProductTypes newProductType = new ProductTypes();
        newProductType.setName(request.getName());
        newProductType.setCategory(category.get());
        newProductType.setDescription(request.getDescription());
        newProductType.setCreateAt(LocalDate.now());
        productTypesRepository.save(newProductType);

        return new ResponseEntity<>("Đã thêm thành công loại sản phẩm " + newProductType.getName(), HttpStatus.OK);
    }
}
