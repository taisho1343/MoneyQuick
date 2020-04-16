package com.payroll.web.central.command.domain.model.payrollcard

data class PayrollCards(val listOfPayrollCard: List<PayrollCard> = listOf()) {

    fun highestPriority(): PayrollCard? = listOfPayrollCard.maxBy { it.priority }

    fun forEachOrderByHighPriority(consumer: (PayrollCard) -> Unit) = listOfPayrollCard.sortedByDescending { it.priority }.forEach(consumer)

}