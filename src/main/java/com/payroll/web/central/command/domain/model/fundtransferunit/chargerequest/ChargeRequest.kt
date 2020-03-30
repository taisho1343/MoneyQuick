package com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest

import com.payroll.web.central.command.domain.exception.IllegalStateEntityException
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus.*
import com.payroll.web.central.command.domain.type.Money

data class ChargeRequest(
        val chargeRequestId: ChargeRequestId,
        private var chargeRequestStatus: ChargeRequestStatus,
        val transferAmountOfMoney: Money,
        val accountForRequest: AccountForRequest
) {

    companion object {
        fun new(
                chargeRequestId: ChargeRequestId,
                transferAmountOfMoney: Money,
                accountForRequest: AccountForRequest
        ) = ChargeRequest(
                chargeRequestId,
                NOT_CHECKED_YET,
                transferAmountOfMoney,
                accountForRequest
        )
    }

    fun chargeRequestStatus() = this.chargeRequestStatus

    fun changeToOK() = when (chargeRequestStatus) {
        NOT_CHECKED_YET -> this.chargeRequestStatus = OK
        OK, NG -> throw IllegalStateEntityException("ChargeRequest status transition is illegal")
    }

    fun changeToNG() = when (chargeRequestStatus) {
        NOT_CHECKED_YET -> this.chargeRequestStatus = NG
        OK, NG -> throw IllegalStateEntityException("ChargeRequest status transition is illegal")
    }

}
