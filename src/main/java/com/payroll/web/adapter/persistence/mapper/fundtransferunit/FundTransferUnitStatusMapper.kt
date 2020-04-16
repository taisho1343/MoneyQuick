package com.payroll.web.adapter.persistence.mapper.fundtransferunit

import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnit
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface FundTransferUnitStatusMapper {

    fun selectFT_UNIT_STATUS_byId_where_latest(id: Long): FT_UNIT_STATUS?

    fun insertFT_UNIT_STATUS(
            @Param("fundTransferUnit") fundTransferUnit: FundTransferUnit,
            @Param("updateDate") updateDate: LocalDateTime
    )

}

data class FT_UNIT_STATUS(
        val ftUnitId: Long,
        val ftUnitStatus: FundTransferUnitStatus,
        val updateDate: LocalDateTime
)