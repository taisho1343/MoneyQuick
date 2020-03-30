package com.payroll.web.central.command.domain.model.invoicestatement

interface InvoiceNumberGenerator {

    fun generate(): InvoiceNumber

}