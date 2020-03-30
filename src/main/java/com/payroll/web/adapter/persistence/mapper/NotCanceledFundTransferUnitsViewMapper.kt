package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface NotCanceledFundTransferUnitsViewMapper {

    fun selectCANCELED_FT_UNITS(id: Long): CANCELED_FT_UNITS?

    fun insertCANCELED_FT_UNITS(
            @Param("fundTransferUnitId") fundTransferUnitId: FundTransferUnitId,
            @Param("canceledDate") canceledDate: LocalDateTime
    )

}

data class NOT_CANCELED_FT_UNITS_VIEW(
        val ftUnitId: Long,
        val chargeDate: LocalDate,
        val createdDate: LocalDateTime,
        val companyId: Long,
        val ftUnitStatus: FundTransferUnitStatus,
        val updateDate: LocalDateTime
)