package com.payroll.web.adapter.persistence.mapper.invoicestatement

import com.payroll.web.central.command.domain.model.invoicestatement.BillingStatus
import com.payroll.web.central.command.domain.model.invoicestatement.PaymentStatus
import com.payroll.web.central.command.domain.model.invoicestatement.PaymentStatusChangeRequest
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Suppress("FunctionName")
@Component
@Mapper
interface InvoiceStatementViewMapper {

    fun selectINVOICE_STATEMENTS_VIEW_byId(invoiceStatementId: Long): INVOICE_STATEMENTS_VIEW?

    fun selectINVOICE_STATEMENTS_VIEW_byFundTransferUnitId(fundTransferUnitId: Long): INVOICE_STATEMENTS_VIEW?

    fun selectINVOICE_STATEMENTS_VIEW_byFundTransferCompanyId(fundTransferCompanyId: Long): List<INVOICE_STATEMENTS_VIEW>

}

data class INVOICE_STATEMENTS_VIEW(
        val invoiceStatementId: Long,
        val invoiceNumber: String,
        val ftUnitId: Long,
        val paymentDeadline: LocalDate,
        val billingStatus: BillingStatus,
        val billingStatusUpdateDate: LocalDateTime,
        val paymentStatus: PaymentStatus,
        val paymentStatusUpdateDate: LocalDateTime,
        val paymentStatusRequest: PaymentStatusChangeRequest,
        val paymentRequestUpdateDate: LocalDateTime
)