package com.payroll.web.adapter.controller.exception

import com.payroll.web.adapter.exception.ResourceNotFoundException

class QueryModelNotFoundException(message: String) : ResourceNotFoundException(message, null)