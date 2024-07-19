package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discounts,Long> {
}
