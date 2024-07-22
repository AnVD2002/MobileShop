package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.Categories;
import com.example.InternAGESTproject.Entity.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypesRepository extends JpaRepository<ProductTypes, Long> {
    @Query("select p from ProductTypes p where p.name=:name")
    Optional<ProductTypes> findByName(@Param("name") String name);
}
