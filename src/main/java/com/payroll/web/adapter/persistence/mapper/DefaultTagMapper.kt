package com.payroll.web.adapter.persistence.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface DefaultTagMapper {

    fun selectDEFAULT_TAGS_byNotificationTemplateId(notificationTemplateId: Long): List<DEFAULT_TAGS>

}

data class DEFAULT_TAGS(
        val tagId: Long,
        val notificationTemplateId: Long
)