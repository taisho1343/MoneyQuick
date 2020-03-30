package com.payroll.web.exception

import java.lang.RuntimeException

open class MoneyQuickException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause)