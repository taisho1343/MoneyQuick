package com.payroll.web.central.command.domain

import com.payroll.web.central.command.domain.event.DomainEventPublisher
import com.payroll.web.central.command.domain.exception.DomainRegistryException
import com.payroll.web.central.command.domain.model.employee.EmployeeAccountGenerator
import com.payroll.web.central.command.domain.model.employee.EmployeeRepository
import com.payroll.web.central.command.domain.model.fundtransferunit.FundTransferUnitRepository
import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceNumberGenerator
import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatementIdGenerator
import com.payroll.web.central.command.domain.model.invoicestatement.InvoiceStatementRepository
import com.payroll.web.central.command.domain.model.invoicestatement.chargeorder.ChargeOrderIdGenerator
import com.payroll.web.central.command.domain.model.payrollcard.PayrollCardRepository
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class DomainRegistry : ApplicationContextAware {

    companion object {
        private var context: ApplicationContext? = null

        fun employeeAccountGenerator(): EmployeeAccountGenerator {
            return notNullContext().getBean(EmployeeAccountGenerator::class.java)
        }

        fun chargeOrderIdGenerator(): ChargeOrderIdGenerator {
            return notNullContext().getBean(ChargeOrderIdGenerator::class.java)
        }

        fun employeeRepository(): EmployeeRepository {
            return notNullContext().getBean(EmployeeRepository::class.java)
        }

        fun payrollCardRepository(): PayrollCardRepository {
            return notNullContext().getBean(PayrollCardRepository::class.java)
        }

        fun invoiceStatementRepository(): InvoiceStatementRepository {
            return notNullContext().getBean(InvoiceStatementRepository::class.java)
        }

        fun fundTransferUnitRepository(): FundTransferUnitRepository {
            return notNullContext().getBean(FundTransferUnitRepository::class.java)
        }

        fun invoiceStatementIdGenerator(): InvoiceStatementIdGenerator {
            return notNullContext().getBean(InvoiceStatementIdGenerator::class.java)
        }

        fun invoiceNumberGenerator(): InvoiceNumberGenerator {
            return notNullContext().getBean(InvoiceNumberGenerator::class.java)
        }

        fun domainEventPublisher(): DomainEventPublisher {
            return notNullContext().getBean(DomainEventPublisher::class.java)
        }


        private fun notNullContext(): ApplicationContext {
            return context ?: throw DomainRegistryException("can't access application context")
        }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

}