package com.payroll.web.adapter.persistence.mapper.notification

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Suppress("FunctionName")
@Component
@Mapper
interface CompanyNotificationMapper {

    fun selectCOM_NOTIFICATION_ID_nextval(): Long

    fun selectCOMPANY_NOTIFICATIONS_byCompanyId(companyId: Long): List<COMPANY_NOTIFICATIONS>

    fun selectCOMPANY_NOTIFICATIONS_byCompanyNotificationId(companyNotificationId: Long): COMPANY_NOTIFICATIONS?

    fun insertCOMPANY_NOTIFICATIONS(companyNotifications: COMPANY_NOTIFICATIONS)

}

@Suppress("ClassName")
data class COMPANY_NOTIFICATIONS(
        val companyNotificationId: Long,
        val title: String,
        val content: String,
        val notificationDate: LocalDate,
        val companyId: Long,
        val notificationTemplateId: Long
)