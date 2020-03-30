package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.company.Company
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface CompanyMapper {

    fun selectCOMPANIES_byId(id: Long): COMPANIES?

    fun insertCOMPANIES(company: Company)

}

data class COMPANIES(
        val companyId: Long,
        val companyName: String,
        val postalCode: String,
        val address: String
)