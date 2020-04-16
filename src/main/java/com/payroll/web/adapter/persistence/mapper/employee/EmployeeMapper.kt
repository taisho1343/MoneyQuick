package com.payroll.web.adapter.persistence.mapper.employee

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface EmployeeMapper {

    fun selectEMPLOYEES_byEmployeeId(employeeId: Long): EMPLOYEES?

}

data class EMPLOYEES(
        val employeeId: Long,
        val companyId: Long,
        val accountHolder: String
)