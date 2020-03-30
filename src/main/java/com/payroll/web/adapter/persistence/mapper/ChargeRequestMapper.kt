package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.type.Money
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface ChargeRequestMapper {

    fun selectCHARGE_REQUEST_ID_nextval(): Long

    fun insertCHARGE_REQUESTS(
            @Param("chargeRequest") chargeRequest: ChargeRequest,
            @Param("fundTransferUnitId") fundTransferUnitId: FundTransferUnitId
    )

}