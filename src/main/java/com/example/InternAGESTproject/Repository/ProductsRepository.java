package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.ProductTypes;
import com.example.InternAGESTproject.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Query("select p from Products p where p.productName=:name")
    Optional<Products> findByName(@Param("name") String name);
}
