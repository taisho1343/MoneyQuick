package com.payroll.web.central.command.domain.model.invoicestatement

import com.payroll.web.exception.IllegalValueObjectException

object PaymentStatusChangeRequestConverter {

    fun convertToPaymentStatus(paymentStatusChangeRequest: PaymentStatusChangeRequest): PaymentStatus {
        return when (paymentStatusChangeRequest) {
            PaymentStatusChangeRequest.PAID -> PaymentStatus.PAID
            PaymentStatusChangeRequest.UNJUST -> PaymentStatus.UNJUST
            PaymentStatusChangeRequest.EXPIRED -> PaymentStatus.EXPIRED
            PaymentStatusChangeRequest.UNKNOWN -> PaymentStatus.UNKNOWN
            PaymentStatusChangeRequest.NONE -> throw IllegalValueObjectException("NONE can't convert PaymentStatus")
        }
    }

}