package com.payroll.web.adapter.persistence.repository.domainrepository

import com.payroll.web.adapter.persistence.mapper.fundtransferunit.*
import com.payroll.web.central.command.domain.model.company.CompanyId
import com.payroll.web.central.command.domain.model.fundtransferunit.*
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.AccountForRequest
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequest
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestId
import com.payroll.web.central.command.domain.type.account.*
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OracleFundTransferUnitRepository(
        val fundTransferUnitMapper: FundTransferUnitMapper,
        val fundTransferUnitStatusMapper: FundTransferUnitStatusMapper,
        val canceledFundTransferUnitMapper: CanceledFundTransferUnitMapper,
        val notCanceledFundTransferUnitsViewMapper: NotCanceledFundTransferUnitsViewMapper,
        val chargeRequestMapper: ChargeRequestMapper,
        val chargeRequestStatusMapper: ChargeRequestStatusMapper,
        val chargeRequestsViewMapper: ChargeRequestsViewMapper
) : FundTransferUnitRepository {

    override fun addFundTransferUnit(fundTransferUnit: FundTransferUnit) {
        val insertionDate = LocalDateTime.now()

        fundTransferUnitMapper.insertFT_UNITS(fundTransferUnit, insertionDate)
        saveFundTransferUnitStatus(fundTransferUnit)

        fundTransferUnit.chargeRequests().forEach {
            chargeRequestMapper.insertCHARGE_REQUESTS(it, fundTransferUnit.fundTransferUnitId)
            chargeRequestStatusMapper.insertCHARGE_REQUEST_STATUS(it, insertionDate)
        }
    }

    override fun updateFundTransferUnit(fundTransferUnit: FundTransferUnit) {
        updateFundTransferUnitStatus(fundTransferUnit)
        fundTransferUnitMapper.updateFT_UNITS(fundTransferUnit)

        fundTransferUnit.chargeRequests().forEach {
            val latestChargeRequestStatus = chargeRequestStatusMapper.selectCHARGE_REQUEST_STATUS_byId_where_latest(it.chargeRequestId.rawId)
                    ?: throw IllegalStateException("chargeRequestStatus not found")
            if (latestChargeRequestStatus.chargeRequestStatus != it.chargeRequestStatus()) {
                chargeRequestStatusMapper.insertCHARGE_REQUEST_STATUS(it, LocalDateTime.now())
            }
            chargeRequestMapper.updateCHARGE_REQUESTS(it, fundTransferUnit.fundTransferUnitId)
        }
    }

    override fun findById(fundTransferUnitId: FundTransferUnitId): FundTransferUnit? {
        return fundTransferUnitMapper.selectFT_UNITS_byId(fundTransferUnitId.rawId)
                ?.let {
                    FundTransferUnit(
                            FundTransferUnitId(it.ftUnitId),
                            fetchFundTransferUnitStatus(it.ftUnitId),
                            ChargeDate(it.chargeDate),
                            fetchChargeRequests(it.ftUnitId),
                            CompanyId(it.companyId)
                    )
                }
    }

    override fun findByStatusReadyToDistribute(): List<FundTransferUnit> {
        return notCanceledFundTransferUnitsViewMapper.selectNOT_CANCELED_FT_UNITS_VIEW_byStatus_where_latest(FundTransferUnitStatus.READY_TO_DISTRIBUTE)
                .map {
                    FundTransferUnit(
                            FundTransferUnitId(it.ftUnitId),
                            it.ftUnitStatus,
                            ChargeDate(it.chargeDate),
                            fetchChargeRequests(it.ftUnitId),
                            CompanyId(it.companyId)
                    )
                }
    }

    private fun fetchFundTransferUnitStatus(fundTransferUnitId: Long): FundTransferUnitStatus {
        return canceledFundTransferUnitMapper.selectCANCELED_FT_UNITS(fundTransferUnitId)
                .let {
                    if (it != null)
                        FundTransferUnitStatus.CANCELED
                    else
                        fundTransferUnitStatusMapper.selectFT_UNIT_STATUS_byId_where_latest(fundTransferUnitId)?.ftUnitStatus
                                ?: throw IllegalStateException("ftUnitStatus not found")
                }
    }

    private fun saveFundTransferUnitStatus(fundTransferUnit: FundTransferUnit) {
        if (fundTransferUnit.fundTransferUnitStatus() == FundTransferUnitStatus.CANCELED) {
            canceledFundTransferUnitMapper.insertCANCELED_FT_UNITS(fundTransferUnit.fundTransferUnitId, LocalDateTime.now())
        } else {
            fundTransferUnitStatusMapper.insertFT_UNIT_STATUS(fundTransferUnit, LocalDateTime.now())
        }
    }

    private fun updateFundTransferUnitStatus(fundTransferUnit: FundTransferUnit) {
        val latestFtUnitStatus = fetchFundTransferUnitStatus(fundTransferUnit.fundTransferUnitId.rawId)
        if (fundTransferUnit.fundTransferUnitStatus() != latestFtUnitStatus) {
            saveFundTransferUnitStatus(fundTransferUnit)
        }
    }

    private fun fetchChargeRequests(fundTransferUnitId: Long): List<ChargeRequest> {
        fun List<CHARGE_REQUESTS_VIEW>.convertToChargeRequests(): List<ChargeRequest> = map {
            ChargeRequest(
                    ChargeRequestId(it.chargeRequestId),
                    it.chargeRequestStatus,
                    it.transferAmountOfMoney,
                    AccountForRequest(
                            AccountHolder(it.accountHolder),
                            Account(
                                    FinancialInstitutionCode(it.accountHolder),
                                    TypeNumber(it.typeNumber),
                                    AccountNumber(it.accountNumber)
                            )
                    )
            )
        }

        return chargeRequestsViewMapper.selectCHARGE_REQUESTS_VIEW_byFundTransferUnitId(fundTransferUnitId)
                .convertToChargeRequests()
    }

}