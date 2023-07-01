package com.org.mortgage.loans.service;

import com.org.mortgage.loans.entity.MortgageChecksEntity;
import com.org.mortgage.loans.mapper.MortgageCheckMapper;
import com.org.mortgage.loans.mapper.MortgageRatesMapper;
import com.org.mortgage.loans.model.MortgageChecks;
import com.org.mortgage.loans.repository.MortgageChecksRepository;
import com.org.mortgage.loans.repository.MortgageRatesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ActiveProfiles("test-it")
@SpringBootTest(classes = MortgageService.class)
class MortgageServiceTest {

    @MockBean
    MortgageRatesRepository mortgageRatesRepository;

    @MockBean
    MortgageChecksRepository mortgageChecksRepository;

    @MockBean
    MortgageCheckMapper mortgageCheckMapper;

    @MockBean
    MortgageRatesMapper mortgageRatesMapper;

    @Autowired
    private MortgageService mortgageService;

    @Test
    void fetchMortgageRates() {
        when(mortgageRatesRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThat(mortgageService.fetchMortgageRates()).isNotNull();
    }

    @Test
    void save() {
        MortgageChecks mortgageChecks = new MortgageChecks();
        mortgageChecks.setHomeValue(BigDecimal.valueOf(100000));
        mortgageChecks.setIncome(BigDecimal.valueOf(10000));
        mortgageChecks.setLoanValue(BigDecimal.valueOf(40000));
        MortgageChecksEntity entity = new MortgageChecksEntity();
        entity.setHomeValue(mortgageChecks.getHomeValue());
        entity.setIncome(mortgageChecks.getIncome());
        entity.setLoanValue(mortgageChecks.getLoanValue());
        entity.setId(1L);
        when(mortgageCheckMapper.toEntity(mortgageChecks)).thenReturn(entity);
        when(mortgageChecksRepository.save(entity)).thenReturn(entity);
        when(mortgageCheckMapper.toVo(entity)).thenReturn(mortgageChecks);
        var checks = mortgageService.save(mortgageChecks);
        Assertions.assertThat(checks).isNotNull();
    }
}