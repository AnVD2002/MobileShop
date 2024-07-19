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
@Table(name = "discounts")
public class Discounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "discount_code")
    private String promoteCode;
    @Column(name = "value")
    private Double value;
    @Column(name = "status")
    private boolean status;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "discount")
    @JsonManagedReference("discount-carts")
    private List<Carts> carts;
}
