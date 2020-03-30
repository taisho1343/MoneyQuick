package com.payroll.web.adapter.persistence.typehandler

import com.payroll.web.adapter.persistence.converter.FundTransferUnitStatusConverter
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitStatus
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

class FundTransferUnitStatusTypeHandler : BaseTypeHandler<FundTransferUnitStatus>() {

    override fun getNullableResult(rs: ResultSet, columnName: String): FundTransferUnitStatus {
        return FundTransferUnitStatusConverter.convertToEnum(rs.getInt(columnName))
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): FundTransferUnitStatus {
        return FundTransferUnitStatusConverter.convertToEnum(rs.getInt(columnIndex))
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): FundTransferUnitStatus {
        return FundTransferUnitStatusConverter.convertToEnum(cs.getInt(columnIndex))
    }

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: FundTransferUnitStatus, jdbcType: JdbcType?) {
        ps.setInt(i, FundTransferUnitStatusConverter.convertToId(parameter))
    }

}