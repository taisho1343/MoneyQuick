package com.payroll.web.central.command.domain.model.payrollcard

import com.payroll.web.central.command.domain.model.fundtransfercompany.FundTransferCompanyId
import com.payroll.web.central.command.domain.type.Money
import com.payroll.web.central.command.domain.type.Priority

data class PayrollCard(
        val payrollCardId: PayrollCardId,
        val cardLabel: CardLabel,
        val desiredChargeMoney: Money,
        val priority: Priority,
        val fundTransferCompanyId: FundTransferCompanyId
)