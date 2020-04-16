package com.payroll.web.adapter.persistence.mapper.fundtransferunit

import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.type.Money
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface ChargeRequestMapper {

    fun selectCHARGE_REQUEST_ID_nextval(): Long

    fun selectCHARGE_REQUESTS_byFundTransferUnitId(fundTransferUnitId: Long): List<CHARGE_REQUESTS>

    fun insertCHARGE_REQUESTS(
            @Param("chargeRequest") chargeRequest: ChargeRequest,
            @Param("fundTransferUnitId") fundTransferUnitId: FundTransferUnitId
    )

    fun updateCHARGE_REQUESTS(
            @Param("chargeRequest") chargeRequest: ChargeRequest,
            @Param("fundTransferUnitId") fundTransferUnitId: FundTransferUnitId
    )

}

data class CHARGE_REQUESTS(
        val chargeRequestId: Long,
        val ftUnitId: Long,
        val transferAmountOfMoney: Money,
        val accountHolder: String,
        val financialInstitutionCode: String,
        val typeNumber: String,
        val accountNumber: String
)