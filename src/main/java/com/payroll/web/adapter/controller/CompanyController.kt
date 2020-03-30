package com.payroll.web.adapter.controller

import com.payroll.web.adapter.controller.exception.QueryModelNotFoundException
import com.payroll.web.central.query.repository.QueryRepositoryCompany
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("companies")
class CompanyController(
        val queryRepositoryCompany: QueryRepositoryCompany
) {

    @GetMapping("{companyId}")
    fun getCompany(@PathVariable("companyId") companyId: Long) = queryRepositoryCompany.findQueryModelCompanyById(companyId)
            ?: throw QueryModelNotFoundException("Company(id = $companyId) not found")

}