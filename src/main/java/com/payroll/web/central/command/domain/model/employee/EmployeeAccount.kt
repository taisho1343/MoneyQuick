package com.payroll.web.central.command.domain.model.employee

import com.payroll.web.central.command.domain.type.account.Account
import com.payroll.web.central.command.domain.type.account.AccountHolder

sealed class EmployeeAccount

data class DisabledEmployeeAccount(val accountHolder: AccountHolder) : EmployeeAccount()

data class EnabledEmployeeAccount(val accountHolder: AccountHolder, val account: Account) : EmployeeAccount()