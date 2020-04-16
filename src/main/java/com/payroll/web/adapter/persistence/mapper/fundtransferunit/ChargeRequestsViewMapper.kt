package com.payroll.web.adapter.persistence.mapper.fundtransferunit

import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus
import com.payroll.web.central.command.domain.type.Money
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface ChargeRequestsViewMapper {

    fun selectCHARGE_REQUESTS_VIEW_byId(id: Long): CHARGE_REQUESTS_VIEW?

    fun selectCHARGE_REQUESTS_VIEW_byFundTransferUnitId(fundTransferUnitId: Long): List<CHARGE_REQUESTS_VIEW>

    fun selectCHARGE_REQUESTS_byStatusAndFundTransferUnitId(
            @Param("chargeRequestStatus") chargeRequestStatus: ChargeRequestStatus,
            @Param("fundTransferUnitId") fundTransferUnitId: Long
    ): List<CHARGE_REQUESTS_VIEW>

}

data class CHARGE_REQUESTS_VIEW(
        val chargeRequestId: Long,
        val ftUnitId: Long,
        val transferAmountOfMoney: Money,
        val accountHolder: String,
        val financialInstitutionCode: String,
        val typeNumber: String,
        val accountNumber: String,
        val chargeRequestStatus: ChargeRequestStatus,
        val updateDate: LocalDateTime
)