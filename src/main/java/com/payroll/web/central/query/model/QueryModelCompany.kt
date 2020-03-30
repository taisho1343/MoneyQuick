package com.payroll.web.central.query.model

data class QueryModelCompany(
        val companyId: Long,
        val companyName: String,
        val representatives: List<String>
)