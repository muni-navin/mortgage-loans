package com.org.mortgage.loans.mapper;

import com.org.mortgage.loans.entity.MortgageRatesEntity;
import com.org.mortgage.loans.model.MortgageRates;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageRatesMapper {

    MortgageRatesEntity toEntity(MortgageRates mortgageRates);

    MortgageRates toVo(MortgageRatesEntity mortgageRatesEntity);


}
