package com.payroll.web.central.exception

import com.payroll.web.exception.MoneyQuickException

open class CentralException(message: String? = null, cause: Throwable? = null) : MoneyQuickException(message, cause)