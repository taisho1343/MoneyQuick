package com.payroll.web.central.command.domain.model.fundtransferunit

interface FundTransferUnitRepository {

    fun addFundTransferUnit(fundTransferUnit: FundTransferUnit)

    //    fun updateFundTransferUnit(fundTransferUnit: FundTransferUnit)
//
    fun findById(fundTransferUnitId: FundTransferUnitId): FundTransferUnit?
//
//    fun findById(fundTransferUnitId: FundTransferUnitId, message: () -> String) =
//            findById(fundTransferUnitId) ?: throw EntityNotFoundException(message())
//
//    fun findByStatusReadyToDistribute(): List<FundTransferUnit>

}