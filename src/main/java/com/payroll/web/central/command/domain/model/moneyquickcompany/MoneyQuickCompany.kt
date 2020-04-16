package com.payroll.web.central.command.domain.model.moneyquickcompany

import com.payroll.web.central.command.domain.model.bank.Bank
import com.payroll.web.central.command.domain.model.company.CompanyName
import com.payroll.web.central.command.domain.type.location.Location
import com.payroll.web.central.command.domain.type.location.PhoneNumber

data class MoneyQuickCompany(
        val moneyQuickCompanyName: CompanyName,
        val moneyQuickCompanyLocation: Location,
        val moneyQuickCompanyPhoneNumber: PhoneNumber,
        val bank: Bank
)