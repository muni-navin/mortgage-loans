package com.org.mortgage.loans.repository;

import com.org.mortgage.loans.entity.MortgageChecksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MortgageChecksRepository extends JpaRepository<MortgageChecksEntity, Long> {
}
