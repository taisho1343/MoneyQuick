package com.payroll.web.adapter.persistence.mapper.invoicestatement

import com.payroll.web.central.command.domain.model.invoicestatement.BillingStatus
import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatement
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface BillingStatusMapper {

    fun selectBILLING_STATUS_byId_where_latest(id: Long): BILLING_STATUS?

    fun insertBILLING_STATUS(
            @Param("invoiceStatement") invoiceStatement: InvoiceStatement,
            @Param("updateDate") updateDate: LocalDateTime
    )

}

data class BILLING_STATUS(
        val invoiceStatementId: Long,
        val billingStatus: BillingStatus,
        val updateDate: LocalDateTime
)