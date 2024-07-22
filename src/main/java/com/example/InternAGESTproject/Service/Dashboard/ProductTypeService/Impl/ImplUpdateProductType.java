package com.example.InternAGESTproject.Service.Dashboard.ProductTypeService.Impl;

import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductTypeRequest.UpdateProductTypeRequest;
import com.example.InternAGESTproject.Entity.Categories;
import com.example.InternAGESTproject.Entity.ProductTypes;
import com.example.InternAGESTproject.Repository.CategoriesRepository;
import com.example.InternAGESTproject.Repository.ProductTypesRepository;
import com.example.InternAGESTproject.Service.Dashboard.ProductTypeService.Interface.UpdateProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
@RequiredArgsConstructor
public class ImplUpdateProductType implements UpdateProductTypeService {

    private final ProductTypesRepository productTypesRepository;

    private final CategoriesRepository categoriesRepository;

    @Override
    public ResponseEntity<?> updateProductType(UpdateProductTypeRequest request) {
        if (request.getProductTypeID() == null) {
            return new ResponseEntity<>("Chưa có loại sản phẩm", HttpStatus.NO_CONTENT);
        }
        if (request.getCategoryID() == null) {
            return new ResponseEntity<>("Chưa có danh mục này", HttpStatus.NO_CONTENT);
        }

        Optional<ProductTypes> productTypeOpt = productTypesRepository.findById(request.getProductTypeID());
        if (productTypeOpt.isEmpty()) {
            return new ResponseEntity<>("Không tồn tại loại sản phẩm này", HttpStatus.NOT_FOUND);
        }

        Optional<Categories> categoryOpt = categoriesRepository.findById(request.getCategoryID());
        if (categoryOpt.isEmpty()) {
            return new ResponseEntity<>("Không tồn tại danh mục này", HttpStatus.NOT_FOUND);
        }

        ProductTypes productType = productTypeOpt.get();

        String name = request.getName();
        if (name != null && !name.isEmpty()) {
            productType.setName(name);
        }

        String description = request.getDescription();
        if (description != null && !description.isEmpty()) {
            productType.setDescription(description);
        }

        Boolean status = request.getStatus();
        if (status != null) {
            productType.setStatus(status);
        }

        productTypesRepository.save(productType);
        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
    }
}
