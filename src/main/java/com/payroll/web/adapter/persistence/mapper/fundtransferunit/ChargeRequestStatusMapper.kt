package com.payroll.web.adapter.persistence.mapper.fundtransferunit

import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface ChargeRequestStatusMapper {

    fun selectCHARGE_REQUEST_STATUS_byId_where_latest(id: Long): CHARGE_REQUEST_STATUS?

    fun insertCHARGE_REQUEST_STATUS(
            @Param("chargeRequest") chargeRequest: ChargeRequest,
            @Param("updateDate") updateDate: LocalDateTime
    )

}

data class CHARGE_REQUEST_STATUS(
        val chargeRequestId: Long,
        val chargeRequestStatus: ChargeRequestStatus,
        val updateDate: LocalDateTime
)