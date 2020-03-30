package com.payroll.web.adapter.controller

import com.payroll.web.adapter.controller.exception.QueryModelNotFoundException
import com.payroll.web.adapter.controller.form.AccountForRequestForm
import com.payroll.web.adapter.controller.form.AccountForm
import com.payroll.web.adapter.controller.form.ChargeRequestForm
import com.payroll.web.adapter.controller.form.FundTransferUnitToAddForm
import com.payroll.web.central.command.applicationservice.AddChargeRequestCommand
import com.payroll.web.central.command.applicationservice.AddFundTransferUnitApplicationService
import com.payroll.web.central.command.applicationservice.AddFundTransferUnitCommand
import com.payroll.web.central.command.domain.model.company.CompanyId
import com.payroll.web.central.command.domain.model.fundtransferunit.ChargeDate
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.AccountForRequest
import com.payroll.web.central.command.domain.type.Money
import com.payroll.web.central.command.domain.type.account.*
import com.payroll.web.central.query.repository.QueryRepositoryFundTransferUnit
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("companies")
class FundTransferUnitController(
        val addFundTransferUnitApplicationService: AddFundTransferUnitApplicationService,
        val queryRepositoryFundTransferUnit: QueryRepositoryFundTransferUnit
) {

    @PostMapping("{companyId}/fundTransferUnits")
    fun addFundTransferUnit(
            @PathVariable("companyId") companyId: Long,
            @RequestBody @Valid fundTransferUnitToAddJson: FundTransferUnitToAddForm,
            uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Void> {
        val fundTransferUnitId = addFundTransferUnitApplicationService.addFundTransferUnit(AddFundTransferUnitCommand(
                chargeDate = ChargeDate(fundTransferUnitToAddJson.chargeDate),
                companyId = CompanyId(companyId),
                chargeRequests = fundTransferUnitToAddJson.chargeRequests.convertToAddChargeRequestCommands()
        ))

        val location = URI.create("/companies/$companyId/fundTransferUnits/${fundTransferUnitId.rawId}")

        val headers = HttpHeaders()
        headers.location = location

        return ResponseEntity(
                headers, HttpStatus.CREATED
        )
    }

    @GetMapping("{companyId}/fundTransferUnits")
    fun getAllFundTransferUnits(@PathVariable("companyId") companyId: Long) =
            queryRepositoryFundTransferUnit.findAllQueryModelFundTransferUnits(companyId)

    @GetMapping("{companyId}/fundTransferUnits/{fundTransferUnitId}")
    fun getFundTransferUnit(
            @PathVariable("companyId") companyId: Long,
            @PathVariable("fundTransferUnitId") fundTransferUnitId: Long
    ) = queryRepositoryFundTransferUnit.findQueryModelFundTransferUnit(fundTransferUnitId)
            ?: throw QueryModelNotFoundException("FundTransferUnit(id = $fundTransferUnitId) not found")

    private fun List<ChargeRequestForm>.convertToAddChargeRequestCommands() = map {
        AddChargeRequestCommand(
                Money.of(it.transferAmountOfMoney),
                it.accountForRequest.convertToAccountForRequest()
        )
    }

    private fun AccountForRequestForm.convertToAccountForRequest() = AccountForRequest(
            AccountHolder(accountHolder),
            account.convertToAccount()
    )

    private fun AccountForm.convertToAccount() = Account(
            FinancialInstitutionCode(financialInstitutionCode),
            TypeNumber(typeNumber),
            AccountNumber(accountNumber)
    )

}