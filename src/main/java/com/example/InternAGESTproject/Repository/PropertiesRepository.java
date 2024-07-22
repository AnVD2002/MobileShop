package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties,Long> {
    @Query("SELECT p FROM Properties p WHERE p.ID IN :ids")
    List<Properties> findAllByIdIn(@Param("ids") List<Long> ids);
}
