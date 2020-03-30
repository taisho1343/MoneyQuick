package com.payroll.web.central.command.domain.model.invoicestatement

import com.payroll.web.central.command.domain.model.fundtransferunit.ChargeDate

object PaymentDeadlineCalculatePolicy {

    fun calculate(chargeDate: ChargeDate): PaymentDeadline {
        return PaymentDeadline(chargeDate.chargeDate.minusDays(2))
    }

}