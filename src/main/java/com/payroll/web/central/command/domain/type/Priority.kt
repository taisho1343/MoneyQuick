package com.payroll.web.central.command.domain.type

import com.payroll.web.exception.IllegalValueObjectException

data class Priority(val value: Int) : Comparable<Priority> {

    companion object {
        fun highest() = Priority(1)
    }

    private val lowerLimitValue = 1

    private val upperLimitValue = 10

    init {
        if ((value < lowerLimitValue) or (value > upperLimitValue)) throw IllegalValueObjectException("Priority value must be between $lowerLimitValue to $upperLimitValue")
    }

    fun decrement() = Priority(value + 1)

    override fun compareTo(other: Priority): Int = when {
        value < other.value -> 1
        value > other.value -> -1
        else -> 0
    }

}