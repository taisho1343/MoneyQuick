package com.payroll.web.central.command.domain.model.invoicestatement

enum class PaymentStatusChangeRequest {
    NONE,
    PAID,
    UNJUST,
    EXPIRED,
    UNKNOWN
}