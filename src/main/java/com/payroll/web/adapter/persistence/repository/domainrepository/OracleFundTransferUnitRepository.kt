package com.payroll.web.adapter.persistence.repository.domainrepository

import com.payroll.web.adapter.persistence.mapper.*
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnit
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitRepository
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class OracleFundTransferUnitRepository(
        val fundTransferUnitMapper: FundTransferUnitMapper,
        val fundTransferUnitStatusMapper: FundTransferUnitStatusMapper,
        val canceledFundTransferUnitMapper: CanceledFundTransferUnitMapper,
        val chargeRequestMapper: ChargeRequestMapper,
        val chargeRequestStatusMapper: ChargeRequestStatusMapper
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

    private fun saveFundTransferUnitStatus(fundTransferUnit: FundTransferUnit) {
        if (fundTransferUnit.fundTransferUnitStatus() == FundTransferUnitStatus.CANCELED) {
            canceledFundTransferUnitMapper.insertCANCELED_FT_UNITS(fundTransferUnit.fundTransferUnitId, LocalDateTime.now())
        } else {
            fundTransferUnitStatusMapper.insertFT_UNIT_STATUS(fundTransferUnit, LocalDateTime.now())
        }
    }

}