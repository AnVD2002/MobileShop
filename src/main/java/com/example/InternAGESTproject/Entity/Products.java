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
    @Column(name = "category_id",insertable = false, updatable = false)
    private Long categoryID;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;
    @Column(name = "price")
    private Double price;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    @JsonManagedReference("product-productVariants")
    List<ProductVariants> productVariants;

    @ManyToOne
    @JsonBackReference("category-products")
    @JoinColumn(name = "category_id")
    private Categories category;

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
