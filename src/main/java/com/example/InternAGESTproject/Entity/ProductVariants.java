package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products_variants")
public class ProductVariants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long ID;
    @Column(name = "product_id",insertable = false,updatable = false)
    private Long productID;
    @Column(name = "property_detail_id",insertable = false,updatable = false)
    private Long propertyDetailID;
    @Column(name = "product_detail_id",insertable = false,updatable = false)
    private Long productDetailID;

    @ManyToOne()
    @JsonBackReference("propertyDetail-productVariants")
    @JoinColumn(name = "property_detail_id")
    private PropertyDetails propertyDetail;

    @ManyToOne()
    @JsonBackReference("product-productVariants")
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne()
    @JsonBackReference("productDetail-productVariants")
    @JoinColumn(name = "product_detail_id")
    private ProductDetails productDetail;
}
