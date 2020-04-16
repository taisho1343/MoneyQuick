package com.payroll.web.central.command.applicationservice

import com.payroll.web.central.command.domain.exception.EntityNotFoundException
import com.payroll.web.central.command.domain.exception.IllegalStateEntityException
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitId
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitRepository
import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatementRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ChangeFundTransferUnitStatusApplicationService(
        val fundTransferUnitRepository: FundTransferUnitRepository,
        val invoiceStatementRepository: InvoiceStatementRepository
) {

    fun changeStatusToGeneratedInvoice(fundTransferUnitId: FundTransferUnitId) {
        checkPreCondition {
            fundTransferUnitRepository.findById(fundTransferUnitId).let { fundTransferUnit ->
                if (fundTransferUnit == null) throw EntityNotFoundException("not found FundTransferUnit by $fundTransferUnitId")
                if (!fundTransferUnit.canChangeToGeneratedInvoice()) throw IllegalStateEntityException("FundTransferUnit status can't change to GeneratedInvoice")
            }
        }

        val fundTransferUnit = fundTransferUnitRepository.findById(fundTransferUnitId) { "not found FundTransferUnit by $fundTransferUnitId" }
        val invoiceStatement = invoiceStatementRepository.findByFundTransferUnitId(fundTransferUnitId) { "not found InvoiceStatement associated by $fundTransferUnitId" }

        invoiceStatement.changeToBilling()
        fundTransferUnit.changeToGeneratedInvoice()

        fundTransferUnitRepository.updateFundTransferUnit(fundTransferUnit)
        invoiceStatementRepository.updateInvoiceStatement(invoiceStatement)
    }

    fun changeStatusToCanceled(fundTransferUnitId: FundTransferUnitId) {
        checkPreCondition {
            fundTransferUnitRepository.findById(fundTransferUnitId).let { fundTransferUnit ->
                if (fundTransferUnit == null) throw EntityNotFoundException("not found FundTransferUnit by $fundTransferUnitId")
                if (!fundTransferUnit.canChangeToCanceled()) throw IllegalStateEntityException("FundTransferUnit status can't change to Canceled")
            }
        }

        val fundTransferUnit = fundTransferUnitRepository.findById(fundTransferUnitId) { "not found FundTransferUnit by $fundTransferUnitId" }

        fundTransferUnit.changeToCanceled()

        fundTransferUnitRepository.updateFundTransferUnit(fundTransferUnit)
    }

}