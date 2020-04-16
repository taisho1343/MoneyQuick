package com.payroll.web.adapter.persistence.repository.queryrepository

import com.payroll.web.adapter.persistence.mapper.fundtransferunit.ChargeRequestMapper
import com.payroll.web.adapter.persistence.mapper.fundtransferunit.ChargeRequestStatusMapper
import com.payroll.web.central.command.domain.exception.IllegalStateEntityException
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus
import com.payroll.web.central.query.model.QueryModelAccountForNGData
import com.payroll.web.central.query.model.QueryModelAccountForRequestForNGData
import com.payroll.web.central.query.model.QueryModelNGData
import com.payroll.web.central.query.repository.QueryRepositoryNGData
import org.springframework.stereotype.Repository

@Repository
class QueryRepositoryNGData(
        val chargeRequestMapper: ChargeRequestMapper,
        val chargeRequestStatusMapper: ChargeRequestStatusMapper
) : QueryRepositoryNGData {

    override fun findQueryModelNGDataByFundTransferUnitId(fundTransferUnitId: Long): List<QueryModelNGData> {
        return chargeRequestMapper.selectCHARGE_REQUESTS_byFundTransferUnitId(fundTransferUnitId)
                .let {
                    if (it.isEmpty()) throw IllegalStateEntityException("chargeRequest not found by fundTransferUnitId = $fundTransferUnitId")
                    it.filter { chargeRequest ->
                        chargeRequestStatusMapper.selectCHARGE_REQUEST_STATUS_byId_where_latest(chargeRequest.chargeRequestId)?.chargeRequestStatus == ChargeRequestStatus.NG
                    }.map { chargeRequest ->
                        QueryModelNGData(
                                chargeRequest.transferAmountOfMoney,
                                QueryModelAccountForRequestForNGData(
                                        chargeRequest.accountHolder,
                                        QueryModelAccountForNGData(
                                                chargeRequest.financialInstitutionCode,
                                                chargeRequest.typeNumber,
                                                chargeRequest.accountNumber
                                        )
                                )
                        )
                    }
                }
    }

}