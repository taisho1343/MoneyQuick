package com.payroll.web.central.command.domain.exception

import com.payroll.web.central.command.exception.CommandException

open class DomainException(message: String? = null, cause: Throwable? = null) : CommandException(message, cause)