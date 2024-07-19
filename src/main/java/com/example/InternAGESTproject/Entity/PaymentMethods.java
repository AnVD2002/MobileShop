package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payment_method")
public class PaymentMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "payment_method_name")
    private String paymentMethodName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentMethod")
    @JsonManagedReference("paymentMethod-payments")
    private List<Payments> payments;
}
