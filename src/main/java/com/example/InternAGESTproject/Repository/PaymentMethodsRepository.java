package com.example.InternAGESTproject.Repository;

import com.example.InternAGESTproject.Entity.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentMethodsRepository extends JpaRepository<PaymentMethods,Long> {
}
