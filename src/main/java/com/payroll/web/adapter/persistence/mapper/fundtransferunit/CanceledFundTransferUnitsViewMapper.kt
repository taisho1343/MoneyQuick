package com.payroll.web.adapter.persistence.mapper.fundtransferunit

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface CanceledFundTransferUnitsViewMapper {

    fun selectCANCELED_FT_UNITS_VIEW_bySearchCriteria(
            @Param("chargeDateFrom") chargeDateFrom: LocalDate?,
            @Param("chargeDateTo") chargeDateTo: LocalDate?
    ): List<CANCELED_FT_UNITS_VIEW>

}

data class CANCELED_FT_UNITS_VIEW(
        val ftUnitId: Long,
        val chargeDate: LocalDate,
        val createdDate: LocalDateTime,
        val companyId: Long,
        val canceledDate: LocalDateTime
)