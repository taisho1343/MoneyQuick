package com.payroll.web.central.command.domain.model.invoicestatement.chargeorder

import com.payroll.web.central.command.domain.type.Money

data class ChargeOrder(
        val chargeOrderId: ChargeOrderId,
        val chargeMoney: Money,
        val chargeTarget: ChargeTarget
)