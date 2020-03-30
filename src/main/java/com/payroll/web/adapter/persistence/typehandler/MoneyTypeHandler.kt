package com.payroll.web.adapter.persistence.typehandler

import com.payroll.web.adapter.persistence.converter.FundTransferUnitStatusConverter
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus
import com.payroll.web.central.command.domain.type.Money
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp

class MoneyTypeHandler : BaseTypeHandler<Money>() {

    override fun getNullableResult(rs: ResultSet, columnName: String): Money {
        return Money.of(rs.getBigDecimal(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): Money {
        return Money.of(rs.getBigDecimal(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): Money {
        return Money.of(cs.getBigDecimal(columnIndex))
    }

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: Money, jdbcType: JdbcType?) {
        ps.setBigDecimal(i, parameter.money)
    }

}