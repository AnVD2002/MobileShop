package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.ProductProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertiesRepository extends JpaRepository<ProductProperties,Long> {
}
