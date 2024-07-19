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
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "product_detail_id", insertable = false, updatable = false)
    private Long productDetailID;
    @Column(name = "order_id" , insertable = false , updatable = false)
    private Long orderID;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total")
    private Double total;
    @Column(name = "cancel_id", insertable = false, updatable = false)
    private Integer cancelID;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    @ManyToOne()
    @JsonBackReference("productDetail-orderDetails")
    @JoinColumn(name = "product_detail_id")
    private ProductDetails productDetail;

    @ManyToOne()
    @JsonBackReference("order-orderDetails")
    @JoinColumn(name ="order_id")
    private Orders order;

}
