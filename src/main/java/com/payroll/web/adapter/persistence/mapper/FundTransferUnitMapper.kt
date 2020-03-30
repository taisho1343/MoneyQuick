package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnit
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface FundTransferUnitMapper {

    fun selectFT_UNIT_ID_nextval(): Long

    fun selectFT_UNITS_byId(id: Long): FT_UNITS?

    fun selectFT_UNITS(): List<FT_UNITS>

    fun insertFT_UNITS(
            @Param("fundTransferUnit") fundTransferUnit: FundTransferUnit,
            @Param("createdDate") createdDate: LocalDateTime
    )

}

data class FT_UNITS(
        val ftUnitId: Long,
        val chargeDate: LocalDate,
        val createdDate: LocalDateTime,
        val companyId: Long
)