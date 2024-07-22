package com.example.InternAGESTproject.Service.Dashboard.ProductService.Impl;

import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductRequest.CreateProductRequest;
import com.example.InternAGESTproject.Entity.ProductProperties;
import com.example.InternAGESTproject.Entity.ProductTypes;
import com.example.InternAGESTproject.Entity.Products;
import com.example.InternAGESTproject.Entity.Properties;
import com.example.InternAGESTproject.Repository.ProductPropertiesRepository;
import com.example.InternAGESTproject.Repository.ProductTypesRepository;
import com.example.InternAGESTproject.Repository.ProductsRepository;
import com.example.InternAGESTproject.Repository.PropertiesRepository;
import com.example.InternAGESTproject.Service.Dashboard.ProductService.Interface.CreateProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ImplCreateProduct implements CreateProductService {
    private final ProductsRepository productsRepository;

    private final ProductTypesRepository productTypesRepository;

    private final PropertiesRepository propertiesRepository;

    private final ProductPropertiesRepository productPropertiesRepository;

    @Override
    public ResponseEntity<?> createProduct(CreateProductRequest request) {
        if(request.getProductName().isEmpty()) {
            return new ResponseEntity<>("Vui long dien ten sp", HttpStatus.NO_CONTENT);
        }
        Optional<Products> product = productsRepository.findByName(request.getProductName());
        if (product.isPresent()){
            return new ResponseEntity<>("Da ton tai sp", HttpStatus.CONFLICT);
        }
        Optional<ProductTypes> productTypes = productTypesRepository.findById(request.getProductTypeID());
        if (productTypes.isEmpty()){
            return new ResponseEntity<>("Khong ton tai loai san pham nay", HttpStatus.NOT_FOUND);
        }

        List<Properties> properties = propertiesRepository.findAllByIdIn(request.getPropertyIDs());
        List<Long> propertyIDs = request.getPropertyIDs();
        Set<Long> foundProperty = properties.stream().map(Properties::getID).collect(Collectors.toSet());
        Set<Long> missProperty = new HashSet<>(propertyIDs);
        missProperty.removeAll(foundProperty);
        if (!missProperty.isEmpty()) {
            return new ResponseEntity<>("Các thuộc tính không tồn tại: " + missProperty, HttpStatus.BAD_REQUEST);
        }

        Products newProduct = new Products();
        newProduct.setCreateAt(LocalDate.now());
        newProduct.setOs(request.getOs());
        newProduct.setCpu(request.getCpu());
        newProduct.setProductName(request.getProductName());
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        newProduct.setStatus(request.getStatus());
        newProduct.setCamera(request.getCamera());
        newProduct.setCameraSelfie((request.getCameraSelfie()));
        newProduct.setRam(request.getRam());
        newProduct.setRom(request.getRom());
        newProduct.setPin(request.getPin());
        newProduct.setDesign(request.getDesign());
        newProduct.setProductType(productTypes.get());

        productsRepository.save(newProduct);

        List<ProductProperties> productProperties = new ArrayList<>();
        for (Properties property : properties) {
            ProductProperties productProperty = new ProductProperties();
            productProperty.setProduct(newProduct);
            productProperty.setProperty(property);
            productProperties.add(productProperty);
        }
        productPropertiesRepository.saveAll(productProperties);
        return new ResponseEntity<>("Tao sar pham " +newProduct.getProductName()+ " thanh cong", HttpStatus.OK);
    }
}
