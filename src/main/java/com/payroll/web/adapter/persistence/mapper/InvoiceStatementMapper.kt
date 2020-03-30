package com.payroll.web.adapter.persistence.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Suppress("FunctionName")
@Component
@Mapper
interface InvoiceStatementMapper {

    fun selectINVOICE_STATEMENTS_byFundTransferUnitId(fundTransferUnitId: Long): INVOICE_STATEMENTS?

}

data class INVOICE_STATEMENTS(
        val invoiceStatementId: Long,
        val invoiceNumber: String,
        val ftUnitId: Long,
        val paymentDeadline: LocalDate
)