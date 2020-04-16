package com.payroll.web.central.command.domain.model.fundtransferunit

import com.payroll.web.central.command.domain.DomainRegistry
import com.payroll.web.central.command.domain.model.distribution.PayrollDistributionService
import com.payroll.web.central.command.domain.model.employee.Employee
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestValidPolicy
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrder
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCards

class IssueInvoiceStatementService {

    companion object {

        fun issueInvoiceStatement(fundTransferUnit: FundTransferUnit) {
            fundTransferUnit.evaluateChargeRequest { ChargeRequestValidPolicy.validChargeToOK(it) }
            val invoiceStatement = fundTransferUnit.issueInvoiceStatement(createChargeOrdersBy(fundTransferUnit.okChargeRequests()))

            DomainRegistry.invoiceStatementRepository().addInvoiceStatement(invoiceStatement)
            DomainRegistry.fundTransferUnitRepository().updateFundTransferUnit(fundTransferUnit)
        }

        private fun createChargeOrdersBy(okChargeRequests: List<ChargeRequest>): List<ChargeOrder> {
            return okChargeRequests.map { createChargeOrderBy(it) }.flatten()
        }

        private fun createChargeOrderBy(chargeRequest: ChargeRequest): List<ChargeOrder> {
            fun findEmployeeIdentifiedAccountBy(chargeRequest: ChargeRequest): Employee {
                val enabledEmployeeAccount = chargeRequest.accountForRequest.convertToEnabledEmployeeAccount()
                return DomainRegistry.employeeRepository().findByEnabledEmployeeAccount(enabledEmployeeAccount) { "not found Employee associated by $enabledEmployeeAccount" }
            }

            fun findHoldingPayrollCardsBy(employee: Employee): PayrollCards {
                val payrollCards = employee.listOfPayrollCardId.map { DomainRegistry.payrollCardRepository().findById(it) { "not found PayrollCard by $it" } }
                return PayrollCards(payrollCards)
            }

            val employee = findEmployeeIdentifiedAccountBy(chargeRequest)
            val payrollCards = findHoldingPayrollCardsBy(employee)

            return PayrollDistributionService.distribute(chargeRequest, payrollCards)
        }

    }

}