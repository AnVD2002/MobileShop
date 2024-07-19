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
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productID;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "ordered")
    private Integer ordered;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productDetail")
    @JsonManagedReference("productDetail-productVariants")
    private List<ProductVariants> productVariants;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "productDetail")
    @JsonManagedReference("productDetail-orderDetails")
    private List<OrderDetails> orderDetails;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "productDetail")
    @JsonManagedReference("productDetail-cartItem")
    private CartItems cartItem;

    @ManyToOne()
    @JsonBackReference("product-productDetails")
    @JoinColumn(name = "product_id")
    private Products product;
}
