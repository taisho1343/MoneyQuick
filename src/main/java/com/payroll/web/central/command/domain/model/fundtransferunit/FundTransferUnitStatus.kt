package com.payroll.web.central.command.domain.model.fundtransferunit

enum class FundTransferUnitStatus {
    READY_TO_DISTRIBUTE,
    DISTRIBUTED_OK,
    DISTRIBUTED_HAS_NG,
    GENERATED_INVOICE,
    CANCELED
}