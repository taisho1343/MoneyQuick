package com.payroll.web.adapter.persistence.mapper.employee

import com.payroll.web.central.command.domain.model.employee.EnabledEmployeeAccount
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface EmployeeViewMapper {

    fun selectEMPLOYEES_VIEW_byId(id: Long): EMPLOYEES_VIEW?

    fun selectEMPLOYEES_VIEW_byEnabledEmployeeAccount(enabledEmployeeAccount: EnabledEmployeeAccount): EMPLOYEES_VIEW?

}

data class EMPLOYEES_VIEW(
        val employeeId: Long,
        val companyId: Long,
        val accountHolder: String,
        val financialInstitutionCode: String,
        val typeNumber: String,
        val accountNumber: String
)