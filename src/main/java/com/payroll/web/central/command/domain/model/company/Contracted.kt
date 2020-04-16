package com.payroll.web.central.command.domain.model.company

import com.payroll.web.central.command.domain.event.DomainEvent
import com.payroll.web.central.command.domain.model.fundtransfercompany.FundTransferCompanyId
import java.time.LocalDateTime

class Contracted(source: Any, val companyId: CompanyId, val fundTransferCompanyId: FundTransferCompanyId) : DomainEvent(source, LocalDateTime.now())