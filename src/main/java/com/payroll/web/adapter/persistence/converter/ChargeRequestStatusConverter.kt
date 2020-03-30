package com.payroll.web.adapter.persistence.converter

import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus

class ChargeRequestStatusConverter {

    companion object {
        private val enumMap = mapOf(
                ChargeRequestStatus.NOT_CHECKED_YET to 1,
                ChargeRequestStatus.OK to 2,
                ChargeRequestStatus.NG to 3
        )

        fun convertToId(status: ChargeRequestStatus): Int {
            return enumMap[status] ?: throw IllegalArgumentException("status doesn't exist")
        }

        fun convertToEnum(id: Int): ChargeRequestStatus {
            return enumMap.filter { it.value == id }.let {
                if (it.size != 1) throw IllegalArgumentException("status error")
                it.keys.first()
            }
        }
    }

}