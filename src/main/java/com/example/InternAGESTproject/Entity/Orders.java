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
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userID;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "update_at")
    private  LocalDate updateAt;
    @Column(name = "number_phone")
    private String numberPhone;
    @Column(name = "status")
    private boolean status;
    @Column(name = "total")
    private Double total;


    @ManyToOne()
    @JsonBackReference("user-orders")
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("order-payment")
    private Payments payment;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "order")
    @JsonManagedReference("order-orderDetails")
    private List<OrderDetails> orderDetails;
}
