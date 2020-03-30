package com.payroll.web.adapter.persistence.typehandler

import com.payroll.web.adapter.persistence.converter.ChargeRequestStatusConverter
import com.payroll.web.central.command.domain.model.fundtransferunit.chargerequest.ChargeRequestStatus
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

class ChargeRequestStatusMapper : BaseTypeHandler<ChargeRequestStatus>() {

    override fun getNullableResult(rs: ResultSet, columnName: String): ChargeRequestStatus {
        return ChargeRequestStatusConverter.convertToEnum(rs.getInt(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): ChargeRequestStatus {
        return ChargeRequestStatusConverter.convertToEnum(rs.getInt(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): ChargeRequestStatus {
        return ChargeRequestStatusConverter.convertToEnum(cs.getInt(columnIndex))
    }

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: ChargeRequestStatus, jdbcType: JdbcType?) {
        ps.setInt(i, ChargeRequestStatusConverter.convertToId(parameter))
    }

}