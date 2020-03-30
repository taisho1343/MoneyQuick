package com.payroll.web.central.command.domain.event

import org.springframework.context.ApplicationEvent
import java.time.LocalDateTime

abstract class DomainEvent(source: Any, val occurredOn: LocalDateTime) : ApplicationEvent(source)