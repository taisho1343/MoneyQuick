package com.payroll.web.adapter.persistence.typehandler

import com.payroll.web.adapter.persistence.converter.FundTransferUnitStatusConverter
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime

class LocalDateTimeTypeHandler : BaseTypeHandler<LocalDateTime>() {

    override fun getNullableResult(rs: ResultSet, columnName: String): LocalDateTime {
        return rs.getTimestamp(columnName).toLocalDateTime()
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): LocalDateTime {
        return rs.getTimestamp(columnIndex).toLocalDateTime()
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): LocalDateTime {
        return cs.getTimestamp(columnIndex).toLocalDateTime()
    }

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: LocalDateTime, jdbcType: JdbcType?) {
        ps.setTimestamp(i, Timestamp.valueOf(parameter))
    }

}