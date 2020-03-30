package com.payroll.web.central.command.domain.model.fundtransferunit

import com.payroll.web.central.command.domain.exception.EntityNotFoundException

interface FundTransferUnitRepository {

    fun addFundTransferUnit(fundTransferUnit: FundTransferUnit)

//    fun updateFundTransferUnit(fundTransferUnit: FundTransferUnit)
//
//    fun findById(fundTransferUnitId: FundTransferUnitId): FundTransferUnit?
//
//    fun findById(fundTransferUnitId: FundTransferUnitId, message: () -> String) =
//            findById(fundTransferUnitId) ?: throw EntityNotFoundException(message())
//
//    fun findByStatusReadyToDistribute(): List<FundTransferUnit>

}