package com.payroll.web.adapter.persistence.mapper.notification

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import java.time.LocalDate

@Suppress("FunctionName")
@Component
@Mapper
interface FundTransferCompanyNotificationMapper {

    fun selectFT_NOTIFICATION_ID_nextval(): Long

    fun selectFT_COMPANY_NOTIFICATIONS_byFundTransferCompanyId(fundTransferCompanyId: Long): List<FT_COMPANY_NOTIFICATIONS>

    fun insertFT_COMPANY_NOTIFICATIONS(ftCompanyNotifications: FT_COMPANY_NOTIFICATIONS)

}

@Suppress("ClassName")
data class FT_COMPANY_NOTIFICATIONS(
        val ftCompanyNotificationId: Long,
        val title: String,
        val notificationDate: LocalDate,
        val ftCompanyId: Long,
        val notificationTemplateId: Long
)