package com.payroll.web.central.command.domain.model.payrollcard

import com.payroll.web.central.command.domain.exception.EntityNotFoundException

interface PayrollCardRepository {

    fun findById(payrollCardId: PayrollCardId): PayrollCard?

    fun findById(payrollCardId: PayrollCardId, message: () -> String) =
            findById(payrollCardId) ?: throw EntityNotFoundException(message())

}