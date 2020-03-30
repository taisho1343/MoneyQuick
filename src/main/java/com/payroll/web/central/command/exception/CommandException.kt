package com.payroll.web.central.command.exception

import com.payroll.web.central.exception.CentralException

open class CommandException(message: String? = null, cause: Throwable? = null) : CentralException(message, cause)