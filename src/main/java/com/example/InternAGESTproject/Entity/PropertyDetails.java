package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "property_details")
public class PropertyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "property_id",insertable = false, updatable = false)
    private Long propertyID;
    @Column(name = "price")
    private Double price;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "propertyDetail")
    @JsonManagedReference("propertyDetail-productVariants")
    private List<ProductVariants> productVariants;

    @ManyToOne
    @JsonBackReference("property-propertyDetails")
    @JoinColumn(name = "property_id")
    private Properties property;
}
