package com.payroll.web.adapter.persistence.mapper

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

    fun insertCHARGE_REQUEST_STATUS(
            @Param("chargeRequest") chargeRequest: ChargeRequest,
            @Param("updateDate") updateDate: LocalDateTime
    )

}