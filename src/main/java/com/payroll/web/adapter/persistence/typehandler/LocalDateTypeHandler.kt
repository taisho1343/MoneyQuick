package com.payroll.web.adapter.persistence.typehandler

import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import java.sql.CallableStatement
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.LocalDate

class LocalDateTypeHandler : BaseTypeHandler<LocalDate>() {

    override fun getNullableResult(rs: ResultSet, columnName: String): LocalDate {
        return rs.getObject(columnName, Date::class.java).toLocalDate()
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): LocalDate {
        return rs.getObject(columnIndex, Date::class.java).toLocalDate()
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): LocalDate {
        return cs.getObject(columnIndex, Date::class.java).toLocalDate()
    }

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: LocalDate, jdbcType: JdbcType?) {
        ps.setDate(i, Date.valueOf(parameter))
    }

}