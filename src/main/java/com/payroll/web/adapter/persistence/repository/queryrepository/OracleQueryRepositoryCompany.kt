package com.payroll.web.adapter.persistence.repository.queryrepository


import com.payroll.web.adapter.persistence.mapper.company.COMPANIES
import com.payroll.web.adapter.persistence.mapper.company.CompanyMapper
import com.payroll.web.adapter.persistence.mapper.company.RepresentativeMapper
import com.payroll.web.central.query.model.QueryModelCompany
import com.payroll.web.central.query.repository.QueryRepositoryCompany
import org.springframework.stereotype.Repository

@Repository
class OracleQueryRepositoryCompany(
        val companyMapper: CompanyMapper,
        val representativeMapper: RepresentativeMapper
) : QueryRepositoryCompany {

    override fun findQueryModelCompanyById(companyId: Long): QueryModelCompany? {
        return companyMapper.selectCOMPANIES_byId(companyId)
                ?.mapToQueryModelCompany()
    }

    private fun COMPANIES.mapToQueryModelCompany(): QueryModelCompany {
        val representatives = representativeMapper.selectREPRESENTATIVES_byCompanyId(companyId)
        if (representatives.isEmpty()) throw IllegalStateException("representative not found")

        return QueryModelCompany(
                companyId,
                companyName,
                representatives.map { it.representativeName }
        )
    }

}