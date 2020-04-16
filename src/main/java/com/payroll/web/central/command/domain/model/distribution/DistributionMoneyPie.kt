package com.payroll.web.central.command.domain.model.distribution

import com.payroll.web.central.command.domain.exception.DistributionException
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCard
import com.payroll.web.central.command.domain.type.Money

typealias Distribution = Pair<PayrollCard, Money>

class DistributionMoneyPie private constructor(
        private val totalSourceMoney: Money,
        private val mapOfAllocation: Map<PayrollCard, Money> = mapOf()
) {

    companion object {

        fun totalSourceMoneyOf(totalSourceMoney: Money): DistributionMoneyPie = DistributionMoneyPie(totalSourceMoney)

    }

    init {
        if (totalSourceMoney < allocatedMoney()) throw DistributionException("total allocated money is over total source distribution money")
    }

    fun requestToAllocate(payrollCard: PayrollCard): DistributionMoneyPie {
        return when {
            isNoRemnantMoney() -> return this
            isOverRemnantMoneyThen(payrollCard.desiredChargeMoney) -> allocate(payrollCard, payrollCard.desiredChargeMoney)
            else -> allocateRemnantMoney(payrollCard)
        }
    }

    fun finish(payrollCard: PayrollCard): Set<Distribution> {
        val finishedDistributionMoneyPie = if (isNoRemnantMoney()) this else addRemnant(payrollCard)
        return finishedDistributionMoneyPie.toDistributions()
    }

    fun remnantMoney(): Money = totalSourceMoney.minusOrThrow(totalOfAllocationMoney()) { IllegalStateException("total allocated money is over total source distribution money") }

    private fun allocate(payrollCard: PayrollCard, money: Money) = DistributionMoneyPie(totalSourceMoney, mapOfAllocation + (payrollCard to money))

    private fun allocatedMoney(): Money = mapOfAllocation.map { it.value }.foldRight(Money.zero()) { money, acc -> money + acc }

    private fun allocateRemnantMoney(payrollCard: PayrollCard) = DistributionMoneyPie(totalSourceMoney, mapOfAllocation + (payrollCard to remnantMoney()))

    private fun addRemnant(payrollCard: PayrollCard) = DistributionMoneyPie(totalSourceMoney, mapOfAllocation.addRemnant(payrollCard, remnantMoney()))

    private fun isOverRemnantMoneyThen(money: Money): Boolean = remnantMoney() > money

    private fun isNoRemnantMoney(): Boolean = remnantMoney() == Money.zero()

    private fun totalOfAllocationMoney(): Money = mapOfAllocation.values.fold(Money.zero()) { acc, money -> acc + money }

    private fun toDistributions(): Set<Distribution> = mapOfAllocation.map { Distribution(it.key, it.value) }.toSet()

    private fun Map<PayrollCard, Money>.addRemnant(payrollCard: PayrollCard, money: Money): Map<PayrollCard, Money> {
        // FIX_EXCEPTION
        val sumMoney = this[payrollCard]?.plus(money)
                ?: throw IllegalArgumentException("payroll card id is not allocated")
        return this.plus(payrollCard to sumMoney)
    }

}