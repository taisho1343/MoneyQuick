package com.payroll.web.adapter.persistence.mapper.employee

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface EmployeeAccountMapper {

    fun selectFINANCIAL_INSTITUTION_CODE_nextval(): String

    fun selectTYPE_NUMBER_nextval(): String

    fun selectACCOUNT_NUMBER_nextval(): String

}