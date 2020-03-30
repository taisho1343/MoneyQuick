package com.payroll.web.central.command.domain.model.employee

import com.payroll.web.central.command.domain.type.account.Account

interface EmployeeAccountGenerator {

    fun generate(): Account

}