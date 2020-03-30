package com.payroll.web.central.command.domain.model.invoicestatement

import com.payroll.web.central.command.domain.exception.EntityNotFoundException
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId

interface InvoiceStatementRepository {

    fun addInvoiceStatement(invoiceStatement: InvoiceStatement)

    fun updateInvoiceStatement(invoiceStatement: InvoiceStatement)

    fun findById(invoiceStatementId: InvoiceStatementId): InvoiceStatement?

    fun findById(invoiceStatementId: InvoiceStatementId, message: () -> String) =
            findById(invoiceStatementId) ?: throw EntityNotFoundException(message())

    fun findByFundTransferUnitId(fundTransferUnitId: FundTransferUnitId): InvoiceStatement?

    fun findByFundTransferUnitId(fundTransferUnitId: FundTransferUnitId, message: () -> String) =
            findByFundTransferUnitId(fundTransferUnitId) ?: throw EntityNotFoundException(message())

}