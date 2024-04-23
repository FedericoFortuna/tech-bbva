package com.tech.bbva.service.repository;

import com.tech.bbva.domain.entity.BankServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankServiceRepository extends JpaRepository<BankServiceEntity, Long> {
}
