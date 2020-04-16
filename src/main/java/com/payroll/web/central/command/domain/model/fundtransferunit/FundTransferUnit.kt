package com.payroll.web.central.command.domain.model.fundtransferunit

import com.payroll.web.central.command.domain.DomainRegistry
import com.payroll.web.central.command.domain.exception.IllegalStateEntityException
import com.payroll.web.central.command.domain.model.company.CompanyId
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus.*
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus.NG
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus.OK
import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatement
import com.payroll.web.central.command.domain.model.invoicestatement.PaymentDeadlineCalculatePolicy
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrder

data class FundTransferUnit(
        val fundTransferUnitId: FundTransferUnitId,
        private var fundTransferUnitStatus: FundTransferUnitStatus,
        val chargeDate: ChargeDate,
        private val chargeRequests: List<ChargeRequest>,
        val companyId: CompanyId
) {

    companion object {
        fun new(
                fundTransferUnitId: FundTransferUnitId,
                chargeDate: ChargeDate,
                chargeRequests: List<ChargeRequest>,
                companyId: CompanyId
        ) = FundTransferUnit(
                fundTransferUnitId,
                READY_TO_DISTRIBUTE,
                chargeDate,
                chargeRequests,
                companyId
        )
    }

    init {
        if (haveDuplicateChargeRequestId()) throw IllegalStateEntityException("FundTransferUnit can't have duplication ChargeRequest")
    }

    fun fundTransferUnitStatus() = this.fundTransferUnitStatus

    fun changeToGeneratedInvoice() {
        checkAllowedTransition(GENERATED_INVOICE)
        this.fundTransferUnitStatus = GENERATED_INVOICE
    }

    fun changeToCanceled() {
        checkAllowedTransition(CANCELED)
        this.fundTransferUnitStatus = CANCELED
    }

    fun canChangeToGeneratedInvoice(): Boolean = canTransition(GENERATED_INVOICE)

    fun canCreateInvoiceStatement(): Boolean = fundTransferUnitStatus == READY_TO_DISTRIBUTE

    fun canChangeToCanceled(): Boolean = canTransition(CANCELED)

    fun chargeRequests() = chargeRequests.map { it.copy() }

    fun ngChargeRequests() = chargeRequests.filter { it.chargeRequestStatus() == NG }.map { it.copy() }

    fun okChargeRequests() = chargeRequests.filter { it.chargeRequestStatus() == OK }.map { it.copy() }

    fun issueInvoiceStatement(chargeOrders: List<ChargeOrder>): InvoiceStatement {
        return InvoiceStatement.new(
                DomainRegistry.invoiceStatementIdGenerator().generate(),
                DomainRegistry.invoiceNumberGenerator().generate(),
                fundTransferUnitId,
                chargeOrders,
                PaymentDeadlineCalculatePolicy.calculate(chargeDate)
        )
    }

    private fun haveDuplicateChargeRequestId() = chargeRequests.groupBy { it.chargeRequestId }.values.any { it.size != 1 }

    fun evaluateChargeRequest(chargeRequestValidator: (ChargeRequest) -> Boolean) {
        checkAllowedTransition(DISTRIBUTED_OK)
        checkAllowedTransition(DISTRIBUTED_HAS_NG)

        val (okChargeRequests, ngChargeRequests) = chargeRequests.partition { chargeRequestValidator(it) }
        fundTransferUnitStatus = if (ngChargeRequests.isNotEmpty()) DISTRIBUTED_HAS_NG else DISTRIBUTED_OK
        okChargeRequests.forEach { it.changeToOK() }
        ngChargeRequests.forEach { it.changeToNG() }
    }

    private fun checkAllowedTransition(to: FundTransferUnitStatus) {
        if (!canTransition(to)) throw IllegalStateEntityException("FundTransferUnit status transition is illegal")
    }

    private fun canTransition(to: FundTransferUnitStatus): Boolean {
        val allowedTransitions = when (fundTransferUnitStatus) {
            READY_TO_DISTRIBUTE -> listOf(DISTRIBUTED_OK, DISTRIBUTED_HAS_NG, CANCELED)
            DISTRIBUTED_OK -> listOf(GENERATED_INVOICE, CANCELED)
            DISTRIBUTED_HAS_NG -> listOf(GENERATED_INVOICE, CANCELED)
            GENERATED_INVOICE -> listOf()
            CANCELED -> listOf()
        }

        return allowedTransitions.contains(to)
    }

}