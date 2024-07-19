package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_properties")
public class ProductProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productID;
    @Column(name = "property_id", insertable = false, updatable = false)
    private Long propertyID;

    @ManyToOne
    @JsonBackReference("product-productProperties")
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JsonBackReference("property-productProperties")
    @JoinColumn(name = "property_id")
    private Properties property;



}
