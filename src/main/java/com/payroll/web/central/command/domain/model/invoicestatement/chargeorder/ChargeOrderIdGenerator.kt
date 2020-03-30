package com.payroll.web.central.command.domain.model.invoicestatement.chargeorder

interface ChargeOrderIdGenerator {

    fun generate(): ChargeOrderId

}