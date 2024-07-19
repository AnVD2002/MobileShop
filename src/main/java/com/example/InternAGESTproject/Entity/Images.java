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
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "product_id", insertable = false , updatable = false)
    private Long productID;
    @Column(name = "highlight")
    private String pictureHighLight;
    @ManyToOne()
    @JsonBackReference("product-images")
    @JoinColumn(name = "product_id")
    private Products product;

}
