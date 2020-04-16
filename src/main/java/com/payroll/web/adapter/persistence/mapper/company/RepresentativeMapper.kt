package com.payroll.web.adapter.persistence.mapper.company

import com.payroll.web.central.command.domain.model.company.Company
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface RepresentativeMapper {

    fun selectREPRESENTATIVES_byCompanyId(companyId: Long): List<REPRESENTATIVES>

    fun insertREPRESENTATIVES(company: Company)

}


data class REPRESENTATIVES(
        val representativeId: Long,
        val representativeName: String,
        val companyId: Long
)