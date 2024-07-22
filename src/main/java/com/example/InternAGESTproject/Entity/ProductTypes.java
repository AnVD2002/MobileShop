package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "product_types")
public class ProductTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryID;
    @Column(name = "name")
    private String name;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
    @JsonManagedReference("productType-products")
    private List<Products> products;

    @ManyToOne()
    @JsonBackReference("category-productTypes")
    @JoinColumn(name = "category_id")
    private Categories category;

}
