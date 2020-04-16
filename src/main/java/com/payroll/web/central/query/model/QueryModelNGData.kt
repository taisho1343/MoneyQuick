package com.payroll.web.central.query.model

import com.payroll.web.central.command.domain.type.Money

data class QueryModelNGData(
        val transferAmountOfMoney: Money,
        val accountForRequest: QueryModelAccountForRequestForNGData
)

data class QueryModelAccountForRequestForNGData(
        val accountHolder: String,
        val account: QueryModelAccountForNGData
)

data class QueryModelAccountForNGData(
        val financialInstitutionCode: String,
        val typeNumber: String,
        val accountNumber: String
)
