package com.payroll.web.central.command.domain.model.employee

import com.payroll.web.central.command.domain.DomainRegistry
import com.payroll.web.central.command.domain.exception.IllegalStateEntityException
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCardId
import com.payroll.web.central.command.domain.type.account.AccountHolder

data class Employee(
        val employeeId: EmployeeId,
        private var employeeAccount: EmployeeAccount,
        var listOfPayrollCardId: List<PayrollCardId>
) {

    companion object {
        fun new(employeeId: EmployeeId, accountHolder: AccountHolder) = Employee(
                employeeId,
                DisabledEmployeeAccount(accountHolder),
                listOf()
        )
    }

    fun accountToEnable() = when (val immutableEmployeeAccount = employeeAccount) {
        is EnabledEmployeeAccount -> throw IllegalStateEntityException("employee account already enable")
        is DisabledEmployeeAccount ->
            this.employeeAccount = EnabledEmployeeAccount(
                    immutableEmployeeAccount.accountHolder,
                    DomainRegistry.employeeAccountGenerator().generate()
            )
    }

    fun hasMatchAccount(otherEmployeeAccount: EnabledEmployeeAccount) = when (employeeAccount) {
        is EnabledEmployeeAccount -> employeeAccount == otherEmployeeAccount
        is DisabledEmployeeAccount -> false
    }

}