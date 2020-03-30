package com.payroll.web.central.command.domain.model.company

import com.payroll.web.central.command.domain.type.location.Location

data class Company(
        val companyId: CompanyId,
        val companyName: CompanyName,
        val location: Location,
        val representatives: List<Representative>
)