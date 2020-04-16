package com.payroll.web.adapter.persistence.mapper.invoicestatement

import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatement
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Suppress("FunctionName")
@Component
@Mapper
interface InvoiceStatementMapper {

    fun selectINVOICE_STATEMENT_ID_nextval(): Long

    fun selectINVOICE_NUMBER_nextval(): String

    fun selectINVOICE_STATEMENTS_byFundTransferUnitId(fundTransferUnitId: Long): INVOICE_STATEMENTS?

    fun insertINVOICE_STATEMENTS(invoiceStatement: InvoiceStatement)

    fun updateINVOICE_STATEMENTS(invoiceStatement: InvoiceStatement)

}

data class INVOICE_STATEMENTS(
        val invoiceStatementId: Long,
        val invoiceNumber: String,
        val ftUnitId: Long,
        val paymentDeadline: LocalDate
)