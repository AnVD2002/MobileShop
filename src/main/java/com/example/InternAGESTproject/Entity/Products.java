package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "product_type_id",insertable = false, updatable = false)
    private Long productTypeID;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;
    @Column(name = "price")
    private Double price;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "screen")
    private String screen;
    @Column(name="os")
    private String os;
    @Column(name = "camera")
    private String camera;
    @Column(name = "camera_selfie")
    private String cameraSelfie;
    @Column(name = "cpu")
    private String cpu;
    @Column(name = "ram")
    private String ram;
    @Column(name = "rom")
    private String rom;
    @Column(name = "pin")
    private String pin;
    @Column(name = "design")
    private String design;



    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    @JsonManagedReference("product-productVariants")
    List<ProductVariants> productVariants;

    @ManyToOne
    @JsonBackReference("productType-products")
    @JoinColumn(name = "product_type_id")
    private ProductTypes productType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonManagedReference("product-productProperties")
    private List<ProductProperties> productProperties;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonManagedReference("product-comments")
    private List<Comments> comments;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "product")
    @JsonManagedReference("product-images")
    private List<Images> images;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonManagedReference("product-productDetails")
    private List<ProductDetails> productDetails;




}
