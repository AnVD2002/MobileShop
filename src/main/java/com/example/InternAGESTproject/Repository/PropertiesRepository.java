package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties,Long> {
}
