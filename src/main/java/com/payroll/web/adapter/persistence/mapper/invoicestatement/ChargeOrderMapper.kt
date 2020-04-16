package com.payroll.web.adapter.persistence.mapper.invoicestatement

import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatementId
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrder
import com.payroll.web.central.command.domain.type.Money
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface ChargeOrderMapper {

    fun selectCHARGE_ORDER_ID_nextval(): Long

    fun selectCHARGE_ORDERS_byInvoiceStatementId(invoiceStatementId: Long): List<CHARGE_ORDERS>

    fun insertCHARGE_ORDERS(
            @Param("chargeOrder") chargeOrder: ChargeOrder,
            @Param("invoiceStatementId") invoiceStatementId: InvoiceStatementId
    )

    fun updateCHARGE_ORDERS(
            @Param("chargeOrder") chargeOrder: ChargeOrder,
            @Param("invoiceStatementId") invoiceStatementId: InvoiceStatementId
    )

}

data class CHARGE_ORDERS(
        val chargeOrderId: Long,
        val chargeMoney: Money,
        val desiredChargeMoney: Money,
        val priority: Int,
        val payrollCardId: Long,
        val ftCompanyId: Long,
        val invoiceStatementId: Long
)