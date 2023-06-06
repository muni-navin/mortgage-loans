package com.org.mortgage.loans.repository;

import com.org.mortgage.loans.entity.MortgageRatesEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ActiveProfiles({"test-it"})
@ExtendWith(SpringExtension.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = MortgageRatesRepository.class)
@EntityScan(basePackageClasses = {MortgageRatesEntity.class})
class MortgageRatesRepositoryTest {

    @Autowired
    MortgageRatesRepository mortgageRatesRepository;

    @Test
    public void test_fetch_All() {
        final List<MortgageRatesEntity> entities = mortgageRatesRepository.findAll();
        Assertions.assertThat(entities).isNotNull();
    }
}