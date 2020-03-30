package com.payroll.web.central.command.domain.type

import com.payroll.web.exception.IllegalValueObjectException
import java.math.BigDecimal

data class Money(val money: BigDecimal) {

    companion object {
        fun zero() = Money(BigDecimal.ZERO)

        fun of(money: Int) = Money(BigDecimal(money))

        fun of(money: BigDecimal) = Money(money)
    }

    init {
        if (money < BigDecimal.ZERO) throw IllegalValueObjectException("Money can't be less than zero")
    }

    operator fun plus(otherMoney: Money) = Money(money + otherMoney.money)

    fun minusOrThrow(otherMoney: Money, throwableLamb: () -> Throwable) = try {
        Money(money - otherMoney.money)
    } catch (e: Throwable) {
        throw throwableLamb()
    }

    operator fun compareTo(otherMoney: Money): Int {
        return when {
            otherMoney.money < money -> 1
            otherMoney.money > money -> -1
            else -> 0
        }
    }

}