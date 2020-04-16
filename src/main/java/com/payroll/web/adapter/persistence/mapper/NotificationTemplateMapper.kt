package com.payroll.web.adapter.persistence.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface NotificationTemplateMapper {

    fun selectNOTIFICATION_TITLE_byId(id: Long): String?

    fun selectNOTIFICATION_CONTENT_byId(id: Long): String?

}