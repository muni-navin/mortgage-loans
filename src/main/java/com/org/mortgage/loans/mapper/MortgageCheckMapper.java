package com.org.mortgage.loans.mapper;

import com.org.mortgage.loans.entity.MortgageChecksEntity;
import com.org.mortgage.loans.model.MortgageChecks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageCheckMapper {

    MortgageChecksEntity toEntity(MortgageChecks mortgageChecks);

    MortgageChecks toVo(MortgageChecksEntity mortgageChecksEntity);
}
