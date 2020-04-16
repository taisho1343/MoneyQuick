package com.payroll.web.adapter.persistence.mapper.invoicestatement

import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatement
import com.payroll.web.central.command.domain.model.invoicestatement.PaymentStatusChangeRequest
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface PaymentStatusChangeRequestMapper {

    fun selectPAYMENT_STATUS_CHANGE_REQUESTS_byId_where_latest(id: Long): PAYMENT_STATUS_REQUESTS?

    fun insertPAYMENT_STATUS_CHANGE_REQUESTS(
            @Param("invoiceStatement") invoiceStatement: InvoiceStatement,
            @Param("updateDate") updateDate: LocalDateTime
    )

}

data class PAYMENT_STATUS_REQUESTS(
        val invoiceStatementId: Long,
        val paymentStatusRequest: PaymentStatusChangeRequest,
        val updateDate: LocalDateTime
)