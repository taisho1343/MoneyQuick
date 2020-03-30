package com.payroll.web.central.command.domain.event

interface DomainEventPublisher {

    fun publish(event: DomainEvent)

}