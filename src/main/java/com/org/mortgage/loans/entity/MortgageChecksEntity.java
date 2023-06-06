package com.org.mortgage.loans.entity;

import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "MORTGAGE_CHECKS")
public class MortgageChecksEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MORTGAGE_CHECKS_ID", updatable = false, nullable = false)
    Long id;

    @Column(name = "INCOME")
    BigDecimal income;

    @Column(name = "MATURITY_PERIOD")
    Integer maturityPeriod;

    @Column(name = "LOAN_VALUE")
    BigDecimal loanValue;

    @Column(name = "HOME_VALUE")
    BigDecimal homeValue;

    @Column(name = "LOAN_GRANTED")
    String loanGranted;
}
