package com.org.mortgage.loans.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.org.mortgage.loans.MortgageServiceApplication;
import com.org.mortgage.loans.exceptions.ExceptionControllerAdvice;
import com.org.mortgage.loans.model.MortgageChecks;
import com.org.mortgage.loans.model.MortgageRates;
import com.org.mortgage.loans.service.MortgageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test-it")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MortgageServiceApplication.class, MortgageServiceController.class, ExceptionControllerAdvice.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MortgageServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MortgageService mortgageService;

    @Test
    void interestRates() throws Exception {
        final MortgageRates mortgageRates = MortgageRates.builder()
                .interestRate(BigDecimal.valueOf(4))
                .maturityPeriod(4).build();
        when(mortgageService.fetchMortgageRates()).thenReturn(Collections.singletonList(mortgageRates));
        mvc.perform(get("/api").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].maturityPeriod", is(4)));

    }

    @Test
    void mortgageCheck() throws Exception {
        final MortgageChecks mortgageChecks = MortgageChecks.builder()
                .income(BigDecimal.valueOf(40000))
                .maturityPeriod(4)
                .homeValue(BigDecimal.valueOf(100000))
                .loanValue(BigDecimal.valueOf(50000)).build();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mortgageChecks);
        when(mortgageService.save(mortgageChecks)).thenReturn(mortgageChecks);
        mvc.perform(post("/api")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.maturityPeriod", is(4)));
    }
}