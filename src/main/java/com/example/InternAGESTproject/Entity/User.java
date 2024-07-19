package com.example.InternAGESTproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;
    @Column(name = "username")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "update_at")
    private LocalDate updateAt;
    @Column(name = "password")
    private String password;
    @Column(name = "role_id",insertable = false,updatable = false)
    private Long role_ID;
    @Column(name = "status")
    private boolean isConfirmed;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "img")
    private String img;
    @Column(name = "number_phone")
    private String numberPhone;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "role_id")
    private Roles role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference("user-orders")
    private List<Orders> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference("user-comments")
    private List<Comments> comments;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonBackReference("cart-user")
    private Carts cart;
}
