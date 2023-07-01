package com.org.mortgage.loans.controllers;

import com.org.mortgage.loans.MortgageServiceApplication;

import com.org.mortgage.loans.exceptions.ExceptionControllerAdvice;
import com.org.mortgage.loans.model.MortgageChecks;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@ActiveProfiles("test-it")
@SpringBootTest(classes = {MortgageServiceController.class, MortgageServiceApplication.class, ExceptionControllerAdvice.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ExtendWith(SpringExtension.class)
class MortgageServiceControllerIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    void interestRates() {
        final ResponseEntity<List> list = restTemplate.getForEntity("/api", List.class);
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    void mortgageCheck_granted() {
        final MortgageChecks mortgageChecks = MortgageChecks.builder()
                .homeValue(BigDecimal.valueOf(100000))
                .loanValue(BigDecimal.valueOf(80000))
                .income(BigDecimal.valueOf(40000))
                .maturityPeriod(4).build();
        final RequestEntity<MortgageChecks> post = RequestEntity.post(URI.create("/api"))
                .body(mortgageChecks);
        final ResponseEntity<MortgageChecks> response = restTemplate.exchange(post, MortgageChecks.class);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody().getLoanGranted()).isEqualTo(true);
    }

    @Test
    void mortgageCheck_not_granted_with_more_loan_value() {
        final MortgageChecks mortgageChecks = MortgageChecks.builder()
                .homeValue(BigDecimal.valueOf(100000))
                .loanValue(BigDecimal.valueOf(8000000))
                .income(BigDecimal.valueOf(40000))
                .maturityPeriod(4).build();
        final RequestEntity<MortgageChecks> post = RequestEntity.post(URI.create("/api"))
                .body(mortgageChecks);
        final ResponseEntity<MortgageChecks> response = restTemplate.exchange(post, MortgageChecks.class);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody().getLoanGranted()).isEqualTo(false);
    }

    @Test
    void mortgageCheck_not_granted_with_4times_income() {
        final MortgageChecks mortgageChecks = MortgageChecks.builder()
                .homeValue(BigDecimal.valueOf(100000))
                .loanValue(BigDecimal.valueOf(4000000))
                .income(BigDecimal.valueOf(4000000))
                .maturityPeriod(4).build();
        final RequestEntity<MortgageChecks> post = RequestEntity.post(URI.create("/api"))
                .body(mortgageChecks);
        final ResponseEntity<MortgageChecks> response = restTemplate.exchange(post, MortgageChecks.class);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody().getLoanGranted()).isEqualTo(false);
    }
}