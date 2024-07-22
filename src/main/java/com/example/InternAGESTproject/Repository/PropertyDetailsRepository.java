package com.example.InternAGESTproject.Repository;


import com.example.InternAGESTproject.Entity.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropertyDetailsRepository extends JpaRepository<PropertyDetails, Long>{
    @Query("select p from PropertyDetails p where p.ID in :ids")
    List<PropertyDetails> findAllByIn(@Param("ids") List<Long> ids);




}
