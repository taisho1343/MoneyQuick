package com.payroll.web.adapter.persistence.repository.queryrepository


import com.payroll.web.adapter.persistence.mapper.fundtransferunit.*
import com.payroll.web.adapter.persistence.mapper.invoicestatement.InvoiceStatementMapper
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus
import com.payroll.web.central.query.model.QueryModelChargeRequest
import com.payroll.web.central.query.model.QueryModelFundTransferUnit
import com.payroll.web.central.query.repository.QueryRepositoryFundTransferUnit
import org.springframework.stereotype.Repository

@Repository
class OracleQueryRepositoryFundTransferUnit(
        val fundTransferUnitMapper: FundTransferUnitMapper,
        val notCanceledFundTransferUnitsViewMapper: NotCanceledFundTransferUnitsViewMapper,
        val canceledFundTransferUnitMapper: CanceledFundTransferUnitMapper,
        val fundTransferUnitStatusMapper: FundTransferUnitStatusMapper,
        val chargeRequestsViewMapper: ChargeRequestsViewMapper,
        val invoiceStatementMapper: InvoiceStatementMapper
) : QueryRepositoryFundTransferUnit {

    override fun findAllQueryModelFundTransferUnits(companyId: Long): List<QueryModelFundTransferUnit> {
        return notCanceledFundTransferUnitsViewMapper.selectNOT_CANCELED_FT_UNITS_VIEW_byCompanyId(companyId).map {
            QueryModelFundTransferUnit(
                    it.ftUnitId,
                    it.companyId,
                    it.createdDate,
                    it.chargeDate,
                    fetchFundTransferUnitStatus(it.ftUnitId),
                    fetchQueryModelChargeRequest(it.ftUnitId),
                    fetchInvoiceStatement(it.ftUnitId)?.invoiceStatementId
            )
        }
    }

    override fun findQueryModelFundTransferUnit(fundTransferUnitId: Long): QueryModelFundTransferUnit? {
        fun FT_UNITS.mapToQueryModelFundTransferUnit() = QueryModelFundTransferUnit(
                ftUnitId,
                companyId,
                createdDate,
                chargeDate,
                fetchFundTransferUnitStatus(ftUnitId),
                fetchQueryModelChargeRequest(ftUnitId),
                fetchInvoiceStatement(ftUnitId)?.invoiceStatementId
        )

        return fundTransferUnitMapper.selectFT_UNITS_byId(fundTransferUnitId)
                ?.mapToQueryModelFundTransferUnit()
    }

    private fun fetchFundTransferUnitStatus(ftUnitId: Long): String {
        return canceledFundTransferUnitMapper.selectCANCELED_FT_UNITS(ftUnitId)
                .let {
                    if (it != null)
                        FundTransferUnitStatus.CANCELED
                    else
                        fundTransferUnitStatusMapper.selectFT_UNIT_STATUS_byId_where_latest(ftUnitId)?.ftUnitStatus
                                ?: throw IllegalStateException("ftUnitStatus not found")
                }.toString()
    }

    private fun fetchQueryModelChargeRequest(ftUnitId: Long): List<QueryModelChargeRequest> {
        fun List<CHARGE_REQUESTS_VIEW>.mapToQueryModelChargeRequests() = map {
            QueryModelChargeRequest(
                    it.chargeRequestId,
                    it.ftUnitId,
                    it.transferAmountOfMoney,
                    it.accountHolder,
                    it.financialInstitutionCode,
                    it.typeNumber,
                    it.accountNumber,
                    it.chargeRequestStatus,
                    it.updateDate
            )
        }

        return chargeRequestsViewMapper.selectCHARGE_REQUESTS_VIEW_byFundTransferUnitId(ftUnitId).mapToQueryModelChargeRequests()
    }

    private fun fetchInvoiceStatement(ftUnitId: Long) = invoiceStatementMapper.selectINVOICE_STATEMENTS_byFundTransferUnitId(ftUnitId)

}