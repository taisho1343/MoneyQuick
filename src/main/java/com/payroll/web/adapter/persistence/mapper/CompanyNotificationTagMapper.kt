package com.payroll.web.adapter.persistence.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface CompanyNotificationTagMapper {

    fun selectCOMPANY_NOTIFICATION_TAGS_byCompanyNotificationId(companyNotificationId: Long): List<COMPANY_NOTIFICATION_TAGS>

    fun insertCOMPANY_NOTIFICATION_TAGS(companyNotificationTags: COMPANY_NOTIFICATION_TAGS)

}

data class COMPANY_NOTIFICATION_TAGS(
        val companyNotificationId: Long,
        val tagId: Long
)