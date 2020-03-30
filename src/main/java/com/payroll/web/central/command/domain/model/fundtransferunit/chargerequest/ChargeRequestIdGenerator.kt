package com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest

interface ChargeRequestIdGenerator {

    fun generate(): ChargeRequestId

}