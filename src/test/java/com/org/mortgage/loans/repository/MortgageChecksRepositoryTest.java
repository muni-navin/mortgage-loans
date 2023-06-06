package com.org.mortgage.loans.repository;

import com.org.mortgage.loans.entity.MortgageChecksEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ActiveProfiles({"test-it"})
@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = MortgageChecksRepository.class)
@EntityScan(basePackageClasses = {MortgageChecksEntity.class})
class MortgageChecksRepositoryTest {

    @Autowired
    MortgageChecksRepository mortgageChecksRepository;

    @Test
    void test_save() {
        final MortgageChecksEntity entity = MortgageChecksEntity.builder()
                .income(new BigDecimal(10000))
                .homeValue(new BigDecimal(40000))
                .loanValue(new BigDecimal(30000)).build();
        final MortgageChecksEntity saved = mortgageChecksRepository.save(entity);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isNotNull();
    }
}