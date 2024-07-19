package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantsRepository extends JpaRepository<ProductVariants, Long> {
}
