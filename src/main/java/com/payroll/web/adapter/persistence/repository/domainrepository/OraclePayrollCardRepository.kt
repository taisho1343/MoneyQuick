package com.payroll.web.adapter.persistence.repository.domainrepository

import com.payroll.web.adapter.persistence.mapper.employee.PayrollCardMapper
import com.payroll.web.central.command.domain.model.fundtransfercompany.FundTransferCompanyId
import com.payroll.web.central.command.domain.model.payrollcard.CardLabel
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCard
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCardId
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCardRepository
import com.payroll.web.central.command.domain.type.Money
import com.payroll.web.central.command.domain.type.Priority
import org.springframework.stereotype.Repository

@Repository
class OraclePayrollCardRepository(val payrollCardMapper: PayrollCardMapper) : PayrollCardRepository {

    override fun findById(payrollCardId: PayrollCardId): PayrollCard? {
        val payrollCard = payrollCardMapper.selectPAYROLL_CARDS_byId(payrollCardId.rawId)
                ?: return null

        return PayrollCard(
                PayrollCardId(payrollCard.payrollCardId),
                CardLabel(payrollCard.cardLabel),
                Money(payrollCard.desiredChargeMoney),
                Priority(payrollCard.priority),
                FundTransferCompanyId(payrollCard.ftCompanyId)
        )
    }

}