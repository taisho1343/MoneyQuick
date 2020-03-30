package com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest

import com.payroll.web.central.command.domain.DomainRegistry

object ChargeRequestValidPolicy {

    fun validChargeToOK(chargeRequest: ChargeRequest): Boolean {
        val enabledEmployeeAccount = chargeRequest.accountForRequest.convertToEnabledEmployeeAccount()
        return DomainRegistry.employeeRepository().existsByEnabledEmployeeAccount(enabledEmployeeAccount)
    }

}