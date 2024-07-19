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
@Table(name = "roles")
public class Roles {
    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long roleID;
    @Column(name= "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role" )
    private List<User> user;
}
