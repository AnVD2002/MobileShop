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
@Table(name = "carts")
public class Carts {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userID;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;
    @Column(name = "total")
    private Double total;
    @Column(name = "discount_id", insertable = false, updatable = false)
    private Long discountID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference("cart-user")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    @JsonManagedReference("cart-cartItems")
    private List<CartItems> cartItems;

    @ManyToOne()
    @JsonBackReference("discount-carts")
    @JoinColumn(name = "discount_id")
    private Discounts discount;

}
