package com.payroll.web.central.command.domain.model.employee

import com.payroll.web.central.command.domain.exception.EntityNotFoundException

interface EmployeeRepository {

    fun findByEnabledEmployeeAccount(enabledEmployeeAccount: EnabledEmployeeAccount): Employee?

    fun findByEnabledEmployeeAccount(enabledEmployeeAccount: EnabledEmployeeAccount, message: () -> String) =
            findByEnabledEmployeeAccount(enabledEmployeeAccount) ?: throw EntityNotFoundException(message())

    fun existsByEnabledEmployeeAccount(enabledEmployeeAccount: EnabledEmployeeAccount): Boolean

}