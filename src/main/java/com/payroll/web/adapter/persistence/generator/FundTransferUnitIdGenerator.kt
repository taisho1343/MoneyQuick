package com.payroll.web.adapter.persistence.generator

import com.payroll.web.adapter.persistence.mapper.FundTransferUnitMapper
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitIdGenerator
import org.springframework.stereotype.Component

@Component
class FundTransferUnitIdGenerator(val fundTransferUnitMapper: FundTransferUnitMapper): FundTransferUnitIdGenerator{

    override fun generate() = FundTransferUnitId(fundTransferUnitMapper.selectFT_UNIT_ID_nextval())

}