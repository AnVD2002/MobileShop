package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsRepository extends JpaRepository<Carts,Integer> {
}
