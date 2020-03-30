package com.payroll.web.central.command.domain.model.employee

interface EmployeeRepository {

    fun existsByEnabledEmployeeAccount(enabledEmployeeAccount: EnabledEmployeeAccount): Boolean

}