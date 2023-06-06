package com.org.mortgage.loans.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MORTGAGE_RATES")
public class MortgageRatesEntity implements Serializable {

    @Id
    @Column(name = "MORTGAGE_RATES_ID")
    Long id;

    @Column(name = "MATURITY_PERIOD")
    Integer maturityPeriod;

    @Column(name = "INTEREST_RATE")
    BigDecimal interestRate;

    @Column(name = "LAST_UPDATED")
    Timestamp lastUpdated;
}
