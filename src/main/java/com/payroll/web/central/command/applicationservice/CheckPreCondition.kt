package com.payroll.web.central.command.applicationservice

import com.payroll.web.central.command.applicationservice.exception.NotValidRequestException

fun checkPreCondition(checker: () -> Unit) {
    runCatching(checker).onFailure { throw NotValidRequestException(cause = it) }
}