package com.payroll.web.central.command.domain.type.account

data class Account(
        val financialInstitutionCode: FinancialInstitutionCode,
        val typeNumber: TypeNumber,
        val accountNumber: AccountNumber
)