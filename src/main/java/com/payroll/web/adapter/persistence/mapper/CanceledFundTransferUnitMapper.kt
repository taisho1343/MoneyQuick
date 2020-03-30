package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface CanceledFundTransferUnitMapper {

    fun selectCANCELED_FT_UNITS(id: Long): CANCELED_FT_UNITS?

    fun insertCANCELED_FT_UNITS(
            @Param("fundTransferUnitId") fundTransferUnitId: FundTransferUnitId,
            @Param("canceledDate") canceledDate: LocalDateTime
    )

}

data class CANCELED_FT_UNITS(
        val fundTransferUnitId: Long,
        val canceledDate: LocalDateTime
)