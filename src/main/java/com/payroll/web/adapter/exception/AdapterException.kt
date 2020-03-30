package com.payroll.web.adapter.exception

import com.payroll.web.exception.MoneyQuickException

open class AdapterException(message: String? = null, cause: Throwable? = null) : MoneyQuickException(message, cause)