package com.org.mortgage.loans.controllers;

import com.org.mortgage.loans.endpoint.MortgageLoansApi;
import com.org.mortgage.loans.model.MortgageChecks;
import com.org.mortgage.loans.model.MortgageRates;
import com.org.mortgage.loans.service.MortgageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class MortgageServiceController implements MortgageLoansApi {

    private final MortgageService mortgageService;

    @Override
    public ResponseEntity<List<MortgageRates>> interestRates() {
        List<MortgageRates> mortgageRates = mortgageService.fetchMortgageRates();
        return new ResponseEntity<>(mortgageRates, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MortgageChecks> mortgageCheck(@Valid @RequestBody MortgageChecks mortgageChecks) {
        mortgageChecks = mortgageService.save(mortgageChecks);
        return new ResponseEntity<>(mortgageChecks, HttpStatus.OK);
    }
}
