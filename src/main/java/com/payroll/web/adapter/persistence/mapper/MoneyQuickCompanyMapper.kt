package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.bank.AccountType
import com.payroll.web.central.command.domain.model.moneyquickcompany.MoneyQuickCompany
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface MoneyQuickCompanyMapper {

    fun selectMQ_COMPANIES_byId(moneyQuickCompanyId: Long): MQ_COMPANIES?

    fun insertMQ_COMPANIES(moneyQuickCompany: MoneyQuickCompany)

}

data class MQ_COMPANIES(
        val mqCompanyId: Long,
        val companyName: String,
        val companyPostalCode: String,
        val companyAddress: String,
        val phoneNumber: String,
        val bankName: String,
        val branchName: String,
        val accountType: AccountType,
        val accountHolder: String,
        val financialInstitutionCode: String,
        val typeNumber: String,
        val accountNumber: String
)