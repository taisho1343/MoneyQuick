package com.payroll.web.adapter.persistence.generator

import com.payroll.web.adapter.persistence.mapper.fundtransferunit.ChargeRequestMapper
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestId
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestIdGenerator
import org.springframework.stereotype.Component

@Component
class ChargeRequestIdGenerator(val chargeRequestMapper: ChargeRequestMapper) : ChargeRequestIdGenerator {

    override fun generate() = ChargeRequestId(chargeRequestMapper.selectCHARGE_REQUEST_ID_nextval())

}