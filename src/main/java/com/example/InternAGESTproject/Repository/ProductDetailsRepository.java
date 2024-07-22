package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
    @Query("SELECT p from ProductDetails p where p.name=:name")
    Optional<ProductDetails> findByName(@Param("name") String name);
}
