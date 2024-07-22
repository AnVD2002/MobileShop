package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    @Query("select c from Categories c where c.name=:name")
    Optional<Categories> findByName(@Param("name") String name);

}
