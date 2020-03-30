package com.payroll.web.central.command.domain.model.invoicestatement.chargeorder

import com.payroll.web.central.command.domain.model.fundtransfercompany.FundTransferCompanyId
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCardId
import com.payroll.web.central.command.domain.type.Money
import com.payroll.web.central.command.domain.type.Priority

data class ChargeTarget(
        val payrollCardId: PayrollCardId,
        val desiredChargeMoney: Money,
        val priority: Priority,
        val fundTransferCompanyId: FundTransferCompanyId
)