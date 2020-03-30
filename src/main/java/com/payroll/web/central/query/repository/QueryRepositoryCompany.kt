package com.payroll.web.central.query.repository

import com.payroll.web.central.query.model.QueryModelCompany

interface QueryRepositoryCompany {

    fun findQueryModelCompanyById(companyId: Long): QueryModelCompany?

}