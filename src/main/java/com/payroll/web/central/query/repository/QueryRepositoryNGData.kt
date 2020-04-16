package com.payroll.web.central.query.repository

import com.payroll.web.central.query.model.QueryModelNGData

interface QueryRepositoryNGData {

    fun findQueryModelNGDataByFundTransferUnitId(fundTransferUnitId: Long): List<QueryModelNGData>

}