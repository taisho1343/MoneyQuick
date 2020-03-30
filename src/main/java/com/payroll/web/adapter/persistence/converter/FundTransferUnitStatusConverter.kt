package com.payroll.web.adapter.persistence.converter

import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus

class FundTransferUnitStatusConverter {

    companion object {
        private val enumMap = mapOf(
                FundTransferUnitStatus.READY_TO_DISTRIBUTE to 1,
                FundTransferUnitStatus.DISTRIBUTED_OK to 2,
                FundTransferUnitStatus.DISTRIBUTED_HAS_NG to 3,
                FundTransferUnitStatus.GENERATED_INVOICE to 4,
                FundTransferUnitStatus.CANCELED to 5
        )

        fun convertToId(status: FundTransferUnitStatus): Int {
            return enumMap[status] ?: throw IllegalArgumentException("status doesn't exist")
        }

        fun convertToEnum(id: Int): FundTransferUnitStatus {
            return enumMap.filter { it.value == id }.let {
                if (it.size != 1) throw IllegalArgumentException("status error")
                it.keys.first()
            }
        }
    }

}