package com.org.mortgage.loans.repository;

import com.org.mortgage.loans.entity.MortgageRatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MortgageRatesRepository extends JpaRepository<MortgageRatesEntity, Long> {
}
