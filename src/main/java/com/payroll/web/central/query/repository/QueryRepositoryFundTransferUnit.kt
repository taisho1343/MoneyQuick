package com.payroll.web.central.query.repository

import com.payroll.web.central.query.model.QueryModelFundTransferUnit

interface QueryRepositoryFundTransferUnit {

    fun findAllQueryModelFundTransferUnits(companyId: Long): List<QueryModelFundTransferUnit>

    fun findQueryModelFundTransferUnit(fundTransferUnitId: Long): QueryModelFundTransferUnit?

}