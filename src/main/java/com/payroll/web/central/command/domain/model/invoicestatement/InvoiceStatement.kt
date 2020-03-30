package com.payroll.web.central.command.domain.model.invoicestatement

import com.payroll.web.central.command.domain.exception.IllegalStateEntityException
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import com.payroll.web.central.command.domain.model.invoicestatement.BillingStatus.BEFORE
import com.payroll.web.central.command.domain.model.invoicestatement.BillingStatus.BILLING
import com.payroll.web.central.command.domain.model.invoicestatement.PaymentStatus.UNCHECKED
import com.payroll.web.central.command.domain.model.invoicestatement.PaymentStatusChangeRequest.NONE
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrder

data class InvoiceStatement(
        val invoiceStatementId: InvoiceStatementId,
        val invoiceNumber: InvoiceNumber,
        val fundTransferUnitId: FundTransferUnitId,
        val chargeOrders: List<ChargeOrder>,
        private var billingStatus: BillingStatus,
        private var paymentStatus: PaymentStatus,
        private var paymentStatusChangeRequest: PaymentStatusChangeRequest,
        val paymentDeadline: PaymentDeadline
) {

    companion object {
        fun new(
                invoiceStatementId: InvoiceStatementId,
                invoiceNumber: InvoiceNumber,
                fundTransferUnitId: FundTransferUnitId,
                chargeOrders: List<ChargeOrder>,
                paymentDeadline: PaymentDeadline
        ) = InvoiceStatement(
                invoiceStatementId,
                invoiceNumber,
                fundTransferUnitId,
                chargeOrders,
                BEFORE,
                UNCHECKED,
                NONE,
                paymentDeadline
        )
    }

    init {
        if (hasDuplicateChargeOrder()) throw IllegalStateEntityException("InvoiceStatement can't have duplication chargeOrder")
        if (hasStatusCombinationsNotAllowed()) throw IllegalStateEntityException("InvoiceStatement illegal status combination")
    }

    fun billingStatus() = this.billingStatus

    fun paymentStatus() = this.paymentStatus

    fun paymentStatusChangeRequest() = this.paymentStatusChangeRequest

    fun changeToBilling() = when (billingStatus) {
        BEFORE -> this.billingStatus = BILLING
        BILLING -> throw IllegalStateEntityException("InvoiceStatement status transition is illegal")
    }

    fun requestChangePaymentStatus(requestedStatus: PaymentStatusChangeRequest) {
        if (!canRequestChangePaymentStatus(requestedStatus))
            throw IllegalStateEntityException("InvoiceStatement can't receive request change payment status to $requestedStatus. InvoiceStatement billing status is $billingStatus.")

        this.paymentStatusChangeRequest = if (isEqualToCurrentPaymentStatus(requestedStatus)) NONE else requestedStatus
    }

    fun approveRequest() = when (paymentStatusChangeRequest) {
        NONE -> throw IllegalStateEntityException("InvoiceStatement hasn't payment status change request")
        else -> {
            paymentStatus = PaymentStatusChangeRequestConverter.convertToPaymentStatus(paymentStatusChangeRequest)
            paymentStatusChangeRequest = NONE
        }
    }

    fun canRequestChangePaymentStatus(newRequestStatus: PaymentStatusChangeRequest) = (newRequestStatus != NONE) and (billingStatus != BEFORE)

    fun canApproveRequest() = paymentStatusChangeRequest != NONE

    private fun isEqualToCurrentPaymentStatus(requestedStatus: PaymentStatusChangeRequest) =
            PaymentStatusChangeRequestConverter.convertToPaymentStatus(requestedStatus) == paymentStatus

    private fun hasDuplicateChargeOrder() = chargeOrders.groupBy { it.chargeOrderId }.values.any { it.size != 1 }

    private fun hasStatusCombinationsNotAllowed() =
            (billingStatus == BEFORE) and !((paymentStatus == UNCHECKED) and (paymentStatusChangeRequest == NONE))

}
