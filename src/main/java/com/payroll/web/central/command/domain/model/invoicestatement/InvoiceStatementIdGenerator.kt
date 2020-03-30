package com.payroll.web.central.command.domain.model.invoicestatement

interface InvoiceStatementIdGenerator {

    fun generate(): InvoiceStatementId

}