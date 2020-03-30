package com.payroll.web.eventpublisher

import com.payroll.web.central.command.domain.event.DomainEvent
import com.payroll.web.central.command.domain.event.DomainEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.stereotype.Component

@Component
class EventPublisher : ApplicationEventPublisherAware, DomainEventPublisher {

    lateinit var publisher: ApplicationEventPublisher

    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        publisher = applicationEventPublisher
    }

    override fun publish(event: DomainEvent) {
        publisher.publishEvent(event)
    }
}