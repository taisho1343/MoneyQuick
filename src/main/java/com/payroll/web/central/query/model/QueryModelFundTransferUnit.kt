package com.payroll.web.central.query.model

import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus
import com.payroll.web.central.command.domain.type.Money
import java.time.LocalDate
import java.time.LocalDateTime

data class QueryModelFundTransferUnit(
        val fundTransferUnitId: Long,
        val companyId: Long,
        val createdAt: LocalDateTime,
        val chargeDate: LocalDate,
        val fundTransferUnitStatus: String,
        val chargeRequestIds: List<QueryModelChargeRequest>,
        val invoiceStatementId: Long?
)

data class QueryModelChargeRequest(
        val chargeRequestId: Long,
        val fundTransferUnitId: Long,
        val transferAmountOfMoney: Money,
        val accountHolder: String,
        val financialInstitutionCode: String,
        val typeNumber: String,
        val accountNumber: String,
        val chargeRequestStatus: ChargeRequestStatus,
        val updateDate: LocalDateTime
)
