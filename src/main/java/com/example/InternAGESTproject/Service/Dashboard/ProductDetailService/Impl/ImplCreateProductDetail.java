package com.example.InternAGESTproject.Service.Dashboard.ProductDetailService.Impl;

import com.example.InternAGESTproject.DTO.Dashboard.Request.ProductDetailRequest.CreateProductDetailRequest;
import com.example.InternAGESTproject.Entity.ProductDetails;
import com.example.InternAGESTproject.Entity.ProductVariants;
import com.example.InternAGESTproject.Entity.Products;
import com.example.InternAGESTproject.Entity.PropertyDetails;
import com.example.InternAGESTproject.Repository.ProductDetailsRepository;
import com.example.InternAGESTproject.Repository.ProductVariantsRepository;
import com.example.InternAGESTproject.Repository.ProductsRepository;
import com.example.InternAGESTproject.Repository.PropertyDetailsRepository;
import com.example.InternAGESTproject.Service.Dashboard.ProductDetailService.Interface.CreateProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ImplCreateProductDetail implements CreateProductDetailService {

    private final PropertyDetailsRepository propertyDetailsRepository;

    private final ProductsRepository productsRepository;

    private final ProductDetailsRepository productDetailsRepository;

    private final ProductVariantsRepository productVariantsRepository;

    @Override
    public ResponseEntity<?> CreateProductDetail(CreateProductDetailRequest request) {
        if(request.getProductID()==null){
            return new ResponseEntity<>("Khong thay id ", HttpStatus.NOT_FOUND);
        }
        Optional<Products> product = productsRepository.findById(request.getProductID());

        if (product.isEmpty()){
            return new ResponseEntity<>("Khong ton tai san pham nay", HttpStatus.NOT_FOUND);
        }
        List<PropertyDetails> propertyDetails = propertyDetailsRepository.findAllByIn(request.getPropertyDetailIDList());
        List<Long> propertyDetailIdList = request.getPropertyDetailIDList();
        Set<Long> foundPropertyDetailIdList = propertyDetails.stream().map(PropertyDetails::getID).collect(Collectors.toSet());
        Set<Long> missingPropertyDetailIdList = new HashSet<>(propertyDetailIdList);
        missingPropertyDetailIdList.removeAll(foundPropertyDetailIdList);
        if(!missingPropertyDetailIdList.isEmpty()) {
            return new ResponseEntity<>("Không có các chi tiết thuộc tính " + missingPropertyDetailIdList, HttpStatus.NOT_FOUND);
        }
        String name = nameProductDetail(product.get().getProductName(), propertyDetails);
        Optional<ProductDetails> productDetail = productDetailsRepository.findByName(name);
        if(productDetail.isPresent()){
            return new ResponseEntity<>("Đã tồn tại ", HttpStatus.CONFLICT);
        }
        Double price = priceProductDetail(product.get().getPrice(),propertyDetails);

        ProductDetails newProductDetail = new ProductDetails();
        newProductDetail.setProduct(product.get());
        newProductDetail.setName(name);
        newProductDetail.setPrice(price);
        newProductDetail.setCreateAt(LocalDate.now());

        productDetailsRepository.save(newProductDetail);

        List<ProductVariants> variants = new ArrayList<>();

        for (PropertyDetails propertyDetail : propertyDetails) {
            ProductVariants newVariant = new ProductVariants();
            newVariant.setProduct(product.get());
            newVariant.setProductDetail(newProductDetail);
            newVariant.setPropertyDetail(propertyDetail);
            variants.add(newVariant);
        }
        productVariantsRepository.saveAll(variants);

        return new ResponseEntity<>("Tao san pham " + name + " thanh cong", HttpStatus.OK);
    }
    /**
     * ten chi tiet san pham
     */
    public String nameProductDetail(String productName, List<PropertyDetails> propertyDetailList){
        StringBuilder name = new StringBuilder();
        for (PropertyDetails propertyDetails : propertyDetailList) {
            name.append(propertyDetails.getName()).append(" ");
        }
        return productName + " " + name;
    }
    /**
     * gia chi tiet san pham
     */
    public Double priceProductDetail(Double price, List<PropertyDetails> propertyDetails){
        Double sum = 0.0;
        for (PropertyDetails propertyDetail : propertyDetails) {
            sum += propertyDetail.getPrice();
        }
        return sum + price;
    }


}
