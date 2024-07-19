package com.example.InternAGESTproject.Repository;


import com.example.InternAGESTproject.Entity.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface PropertyDetailsRepository extends JpaRepository<PropertyDetails, Long>{

}
