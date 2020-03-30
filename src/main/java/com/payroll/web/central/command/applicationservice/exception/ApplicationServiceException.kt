package com.payroll.web.central.command.applicationservice.exception

import com.payroll.web.central.command.exception.CommandException

open class ApplicationServiceException(message: String? = null, cause: Throwable? = null) : CommandException(message, cause)