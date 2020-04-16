package com.payroll.web.central.command.domain.model.distribution

import com.payroll.web.central.command.domain.DomainRegistry
import com.payroll.web.central.command.domain.exception.DistributionException
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrder
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeTarget
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCards

class PayrollDistributionService {

    companion object {

        fun distribute(chargeRequest: ChargeRequest, payrollCards: PayrollCards): List<ChargeOrder> {
            val highestPriorityPayrollCard = payrollCards.highestPriority()
                    ?: throw DistributionException("not found payroll card")

            var distributionMoneyPie = DistributionMoneyPie.totalSourceMoneyOf(chargeRequest.transferAmountOfMoney)
            payrollCards.forEachOrderByHighPriority { distributionMoneyPie = distributionMoneyPie.requestToAllocate(it) }
            return distributionMoneyPie.finish(highestPriorityPayrollCard).mapChargeOrders()
        }

        private fun Set<Distribution>.mapChargeOrders(): List<ChargeOrder> = map { (payrollCard, chargeMoney) ->
            ChargeOrder(
                    DomainRegistry.chargeOrderIdGenerator().generate(),
                    chargeMoney,
                    ChargeTarget(
                            payrollCard.payrollCardId,
                            payrollCard.desiredChargeMoney,
                            payrollCard.priority,
                            payrollCard.fundTransferCompanyId
                    )
            )
        }

    }

}