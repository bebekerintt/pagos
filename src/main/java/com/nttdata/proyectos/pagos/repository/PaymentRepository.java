package com.nttdata.proyectos.pagos.repository;

import com.nttdata.proyectos.pagos.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}