package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "cart_id" , insertable = false, updatable = false)
    private Long cartID;
    @Column(name = "product_detail_id", insertable = false, updatable = false)
    private Long productDetailID;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "img")
    private String img;
    @Column(name = "description")
    private String description;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;

    @ManyToOne()
    @JsonBackReference("cart-cartItems")
    @JoinColumn(name = "cart_id")
    private Carts cart;

    @OneToOne()
    @JsonBackReference("productDetail-cartItem")
    @JoinColumn(name = "product_detail_id")
    private ProductDetails productDetail;
}
