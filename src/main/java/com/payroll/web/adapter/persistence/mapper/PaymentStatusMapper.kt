package com.payroll.web.adapter.persistence.mapper

import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatement
import com.payroll.web.central.command.domain.model.invoicestatement.PaymentStatus
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface PaymentStatusMapper {

    fun selectPAYMENT_STATUS_byId_where_latest(id: Long): PAYMENT_STATUS?

    fun insertPAYMENT_STATUS(
            @Param("invoiceStatement") invoiceStatement: InvoiceStatement,
            @Param("updateDate") updateDate: LocalDateTime
    )

}

data class PAYMENT_STATUS(
        val invoiceStatementId: Long,
        val paymentStatus: PaymentStatus,
        val updateDate: LocalDateTime
)