package com.payroll.web.adapter.persistence.repository.domainrepository

import com.payroll.web.adapter.persistence.mapper.EMPLOYEES_VIEW
import com.payroll.web.adapter.persistence.mapper.EmployeeViewMapper
import com.payroll.web.adapter.persistence.mapper.PAYROLL_CARDS
import com.payroll.web.adapter.persistence.mapper.PayrollCardMapper
import com.payroll.web.central.command.domain.model.employee.Employee
import com.payroll.web.central.command.domain.model.employee.EmployeeId
import com.payroll.web.central.command.domain.model.employee.EmployeeRepository
import com.payroll.web.central.command.domain.model.employee.EnabledEmployeeAccount
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCardId
import com.payroll.web.central.command.domain.type.account.*
import org.springframework.stereotype.Repository

@Repository
class OracleEmployeeRepository(
        val employeeViewMapper: EmployeeViewMapper,
        val payrollCardMapper: PayrollCardMapper
) : EmployeeRepository {

    override fun findByEnabledEmployeeAccount(enabledEmployeeAccount: EnabledEmployeeAccount): Employee? {
        return employeeViewMapper.selectEMPLOYEES_VIEW_byEnabledEmployeeAccount(enabledEmployeeAccount)
                ?.mapToEmployee()
    }

    override fun existsByEnabledEmployeeAccount(enabledEmployeeAccount: EnabledEmployeeAccount): Boolean {
        return employeeViewMapper.selectEMPLOYEES_VIEW_byEnabledEmployeeAccount(enabledEmployeeAccount) != null
    }

    private fun EMPLOYEES_VIEW.mapToEmployee(): Employee {
        fun fetchEmployeeAccount() = EnabledEmployeeAccount(
                AccountHolder(accountHolder),
                Account(
                        FinancialInstitutionCode(financialInstitutionCode),
                        TypeNumber(typeNumber),
                        AccountNumber(accountNumber)
                )
        )

        fun List<PAYROLL_CARDS>.convertToPayrollCardIds(): List<PayrollCardId> = map { PayrollCardId(it.payrollCardId) }

        val payrollCards = payrollCardMapper.selectPAYROLL_CARDS_byEmployeeId(employeeId)

        return Employee(
                EmployeeId(employeeId),
                fetchEmployeeAccount(),
                payrollCards.convertToPayrollCardIds()
        )
    }

}