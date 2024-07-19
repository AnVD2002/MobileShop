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
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "parent_id")
    private Long parentID;
    @Column(name = "content")
    private String content;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;
    @Column(name = "rating")
    private Integer rating;
    @Column(name="product_id", insertable = false, updatable = false)
    private Long productID;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userID;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    @JsonBackReference("product-comments")
    private Products product;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-comments")
    private User user;


}
