package com.payroll.web.central.command.domain.model.bank

import com.payroll.web.central.command.domain.type.account.Account
import com.payroll.web.central.command.domain.type.account.AccountHolder

data class Bank(
        val bankName: BankName,
        val branchName: BranchName,
        val accountType: AccountType,
        val accountHolder: AccountHolder,
        val account: Account
)