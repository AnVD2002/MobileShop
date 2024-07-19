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
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "order_id", insertable = false, updatable = false)
    private Long orderID;
    @Column(name = "payment_method_id", insertable = false, updatable = false)
    private Long paymentMethodID;
    @Column(name = "status")
    private boolean status;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "create_at")
    private LocalDate createAt;

    @OneToOne()
    @JsonBackReference("order-payment")
    @JoinColumn(name = "order_id")
    private Orders order;


    @ManyToOne
    @JsonBackReference("paymentMethod-payments")
    @JoinColumn(name = "payment_method_id")
    private PaymentMethods paymentMethod;

}
