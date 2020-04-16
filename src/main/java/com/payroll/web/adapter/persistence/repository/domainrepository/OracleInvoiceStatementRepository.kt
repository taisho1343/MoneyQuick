package com.payroll.web.adapter.persistence.repository.domainrepository

import com.payroll.web.adapter.persistence.mapper.*
import com.payroll.web.central.command.domain.model.fundtransfercompany.FundTransferCompanyId
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import com.payroll.web.central.command.domain.model.invoicestatement.*
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrder
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrderId
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeTarget
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCardId
import com.payroll.web.central.command.domain.type.Priority
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OracleInvoiceStatementRepository(
        val invoiceStatementMapper: InvoiceStatementMapper,
        val billingStatusMapper: BillingStatusMapper,
        val paymentStatusMapper: PaymentStatusMapper,
        val paymentStatusChangeRequestMapper: PaymentStatusChangeRequestMapper,
        val invoiceStatementViewMapper: InvoiceStatementViewMapper,
        val chargeOrderMapper: ChargeOrderMapper
) : InvoiceStatementRepository {

    override fun addInvoiceStatement(invoiceStatement: InvoiceStatement) {
        val insertTime = LocalDateTime.now()

        invoiceStatementMapper.insertINVOICE_STATEMENTS(invoiceStatement)
        billingStatusMapper.insertBILLING_STATUS(invoiceStatement, insertTime)
        paymentStatusMapper.insertPAYMENT_STATUS(invoiceStatement, insertTime)
        paymentStatusChangeRequestMapper.insertPAYMENT_STATUS_CHANGE_REQUESTS(invoiceStatement, insertTime)

        invoiceStatement.chargeOrders.forEach {
            chargeOrderMapper.insertCHARGE_ORDERS(it, invoiceStatement.invoiceStatementId)
        }
    }

    override fun updateInvoiceStatement(invoiceStatement: InvoiceStatement) {
        val updateTime = LocalDateTime.now()

        val latestBillingStatus = billingStatusMapper.selectBILLING_STATUS_byId_where_latest(invoiceStatement.invoiceStatementId.rawId)
                ?: throw IllegalStateException("billingStatus not found")
        if (latestBillingStatus.billingStatus != invoiceStatement.billingStatus()) {
            billingStatusMapper.insertBILLING_STATUS(invoiceStatement, updateTime)
        }

        val latestPaymentStatus = paymentStatusMapper.selectPAYMENT_STATUS_byId_where_latest(invoiceStatement.invoiceStatementId.rawId)
                ?: throw IllegalStateException("paymentStatus not found")
        if (latestPaymentStatus.paymentStatus != invoiceStatement.paymentStatus()) {
            paymentStatusMapper.insertPAYMENT_STATUS(invoiceStatement, updateTime)
        }

        val latestPaymentStatusChangeRequest = paymentStatusChangeRequestMapper.selectPAYMENT_STATUS_CHANGE_REQUESTS_byId_where_latest(invoiceStatement.invoiceStatementId.rawId)
                ?: throw IllegalStateException("paymentStatusChangeRequest not found")
        if (latestPaymentStatusChangeRequest.paymentStatusRequest != invoiceStatement.paymentStatusChangeRequest()) {
            paymentStatusChangeRequestMapper.insertPAYMENT_STATUS_CHANGE_REQUESTS(invoiceStatement, updateTime)
        }

        invoiceStatement.chargeOrders.forEach {
            chargeOrderMapper.updateCHARGE_ORDERS(it, invoiceStatement.invoiceStatementId)
        }

        invoiceStatementMapper.updateINVOICE_STATEMENTS(invoiceStatement)
    }

    override fun findById(invoiceStatementId: InvoiceStatementId): InvoiceStatement? {
        return invoiceStatementViewMapper.selectINVOICE_STATEMENTS_VIEW_byId(invoiceStatementId.rawId)
                ?.mapToInvoiceStatement()
    }

    override fun findByFundTransferUnitId(fundTransferUnitId: FundTransferUnitId): InvoiceStatement? {
        return invoiceStatementViewMapper.selectINVOICE_STATEMENTS_VIEW_byFundTransferUnitId(fundTransferUnitId.rawId)
                ?.mapToInvoiceStatement()
    }

    private fun INVOICE_STATEMENTS_VIEW.mapToInvoiceStatement(): InvoiceStatement {
        fun List<CHARGE_ORDERS>.convertToChargeOrders(): List<ChargeOrder> = map {
            ChargeOrder(
                    ChargeOrderId(it.chargeOrderId),
                    it.chargeMoney,
                    ChargeTarget(
                            PayrollCardId(it.payrollCardId),
                            it.desiredChargeMoney,
                            Priority(it.priority),
                            FundTransferCompanyId(it.ftCompanyId)
                    )
            )
        }

        val chargeOrders = chargeOrderMapper.selectCHARGE_ORDERS_byInvoiceStatementId(invoiceStatementId)

        return InvoiceStatement(
                InvoiceStatementId(invoiceStatementId),
                InvoiceNumber(invoiceNumber),
                FundTransferUnitId(ftUnitId),
                chargeOrders.convertToChargeOrders(),
                billingStatus,
                paymentStatus,
                paymentStatusRequest,
                PaymentDeadline(paymentDeadline)
        )
    }

}