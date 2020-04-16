package com.payroll.web.adapter.controller.errorhandler

import com.payroll.web.adapter.exception.ResourceNotFoundException
import com.payroll.web.central.command.applicationservice.exception.NotValidRequestException
import com.payroll.web.exception.MoneyQuickException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.util.ErrorHandler
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalErrorHandler {

    private val logger = LoggerFactory.getLogger(ErrorHandler::class.java)

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNotValidRequestException(exception: NotValidRequestException) {
        logger.info("error occurred", exception)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException) {
        logger.info("error occurred", exception)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleMoneyQuickException(exception: MoneyQuickException) {
        logger.info("error occurred", exception)
    }

}