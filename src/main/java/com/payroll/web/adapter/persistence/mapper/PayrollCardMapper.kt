package com.payroll.web.adapter.persistence.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface PayrollCardMapper {

    fun selectPAYROLL_CARD_ID_nextval(): Long

    fun selectPAYROLL_CARDS_byId(payrollCardId: Long): PAYROLL_CARDS?

    fun selectPAYROLL_CARDS_byEmployeeId(employeeId: Long): List<PAYROLL_CARDS>

}

data class PAYROLL_CARDS(
        val payrollCardId: Long,
        val cardLabel: String,
        val desiredChargeMoney: BigDecimal,
        val priority: Int,
        val employeeId: Long,
        val ftCompanyId: Long,
        val updateDate: LocalDateTime
)