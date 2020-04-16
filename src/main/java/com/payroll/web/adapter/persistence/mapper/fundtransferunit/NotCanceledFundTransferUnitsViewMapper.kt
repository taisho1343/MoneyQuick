package com.payroll.web.adapter.persistence.mapper.fundtransferunit

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

    fun selectNOT_CANCELED_FT_UNITS_VIEW_byId(id: Long): NOT_CANCELED_FT_UNITS_VIEW?

    fun selectNOT_CANCELED_FT_UNITS_VIEW_byStatus_where_latest(status: FundTransferUnitStatus): List<NOT_CANCELED_FT_UNITS_VIEW>

    fun selectNOT_CANCELED_FT_UNITS_VIEW_byCompanyId(companyId: Long): List<NOT_CANCELED_FT_UNITS_VIEW>

    fun selectNOT_CANCELED_FT_UNITS_VIEW_bySearchCriteria(
            @Param("chargeDateFrom") chargeDateFrom: LocalDate?,
            @Param("chargeDateTo") chargeDateTo: LocalDate?,
            @Param("fundTransferUnitStatus") fundTransferUnitStatus: FundTransferUnitStatus?
    ): List<NOT_CANCELED_FT_UNITS_VIEW>

}

data class NOT_CANCELED_FT_UNITS_VIEW(
        val ftUnitId: Long,
        val chargeDate: LocalDate,
        val createdDate: LocalDateTime,
        val companyId: Long,
        val ftUnitStatus: FundTransferUnitStatus,
        val updateDate: LocalDateTime
)