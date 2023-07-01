package com.org.mortgage.loans.service;

import com.org.mortgage.loans.entity.MortgageRatesEntity;
import com.org.mortgage.loans.mapper.MortgageCheckMapper;
import com.org.mortgage.loans.mapper.MortgageRatesMapper;
import com.org.mortgage.loans.model.MortgageChecks;
import com.org.mortgage.loans.model.MortgageRates;
import com.org.mortgage.loans.repository.MortgageChecksRepository;
import com.org.mortgage.loans.repository.MortgageRatesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MortgageService {

    private final MortgageRatesRepository mortgageRatesRepository;

    private final MortgageChecksRepository mortgageChecksRepository;

    private final MortgageCheckMapper mortgageCheckMapper;

    private final MortgageRatesMapper mortgageRatesMapper;

    public List<MortgageRates> fetchMortgageRates() {
        List<MortgageRates> list = new ArrayList<>();
        Optional.of(mortgageRatesRepository.findAll()).ifPresent(rates -> mapMortgageRates(rates, list));
        return list;
    }

    private void mapMortgageRates(List<MortgageRatesEntity> rates, List<MortgageRates> list) {
        rates.forEach(rate -> list.add(mortgageRatesMapper.toVo(rate)));
    }

    public MortgageChecks save(MortgageChecks mortgageChecks) {
        int incomeCheck = mortgageChecks.getLoanValue().compareTo(mortgageChecks.getIncome().multiply(new BigDecimal("4")));
        int homeValCheck = mortgageChecks.getLoanValue().compareTo(mortgageChecks.getHomeValue());
        mortgageChecks.setLoanGranted((incomeCheck <= 0) && (homeValCheck <= 0));
        return mortgageCheckMapper.toVo(mortgageChecksRepository.save(mortgageCheckMapper.toEntity(mortgageChecks)));
    }
}
