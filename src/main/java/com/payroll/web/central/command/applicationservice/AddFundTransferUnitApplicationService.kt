package com.payroll.web.central.command.applicationservice

import com.payroll.web.central.command.domain.model.company.CompanyId
import com.payroll.web.central.command.domain.model.fundtransferunit.*
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.AccountForRequest
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestIdGenerator
import com.payroll.web.central.command.domain.type.Money
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddFundTransferUnitApplicationService(
        val fundTransferUnitRepository: FundTransferUnitRepository,
        val fundTransferUnitIdGenerator: FundTransferUnitIdGenerator,
        val chargeRequestIdGenerator: ChargeRequestIdGenerator
) {

    fun addFundTransferUnit(command: AddFundTransferUnitCommand): FundTransferUnitId {
        val fundTransferUnit = command.convertToFundTransferUnit()

        fundTransferUnitRepository.addFundTransferUnit(fundTransferUnit)
        return fundTransferUnit.fundTransferUnitId
    }

    private fun AddFundTransferUnitCommand.convertToFundTransferUnit(): FundTransferUnit {
        fun convertChargeRequests(commands: List<AddChargeRequestCommand>) = commands.map{
            ChargeRequest.new(
                    chargeRequestIdGenerator.generate(),
                    it.transferAmountOfMoney,
                    it.accountForRequest
            )
        }

        return FundTransferUnit.new(
                fundTransferUnitIdGenerator.generate(),
                chargeDate,
                convertChargeRequests(chargeRequests),
                companyId
        )
    }

}

data class AddFundTransferUnitCommand(
        val chargeDate: ChargeDate,
        val companyId: CompanyId,
        val chargeRequests: List<AddChargeRequestCommand>
)

data class AddChargeRequestCommand(
        val transferAmountOfMoney: Money,
        val accountForRequest: AccountForRequest
)