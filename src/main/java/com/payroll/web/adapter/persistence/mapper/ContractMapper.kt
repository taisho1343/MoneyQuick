package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.company.CompanyId
import com.payroll.web.central.command.domain.model.fundtransfercompany.FundTransferCompanyId
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface ContractMapper {

    fun selectCONTRACTS_for_fundTransferCompany_byFundTransferCompanyId(fundTransferCompanyId: Long): List<CONTRACTS>

    fun insertCONTRACTS(
            @Param("companyId") companyId: CompanyId,
            @Param("fundTransferCompanyId") fundTransferCompanyId: FundTransferCompanyId,
            @Param("createdDate") createdDate: LocalDateTime
    )

}

data class CONTRACTS(
        val companyId: Long,
        val ftCompanyId: Long,
        val createdDate: LocalDateTime
)