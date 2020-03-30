package com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest

import com.payroll.web.central.command.domain.model.employee.EnabledEmployeeAccount
import com.payroll.web.central.command.domain.type.account.Account
import com.payroll.web.central.command.domain.type.account.AccountHolder

data class AccountForRequest(
        val accountHolder: AccountHolder,
        val account: Account
) {

    fun convertToEnabledEmployeeAccount() = EnabledEmployeeAccount(accountHolder, account)

}
