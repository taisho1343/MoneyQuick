package com.payroll.web.adapter.persistence.mapper.company

import com.payroll.web.central.command.domain.model.fundtransfercompany.FundTransferCompany
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface FundTransferCompanyMapper {

    fun selectFT_COMPANIES_ById(fundTransferCompanyId: Long): FT_COMPANIES?

    fun insertFT_COMPANIES(fundTransferCompany: FundTransferCompany)

}

data class FT_COMPANIES(
        val ftCompanyId: Long,
        val ftCompanyNumber: String,
        val ftCompanyName: String
)