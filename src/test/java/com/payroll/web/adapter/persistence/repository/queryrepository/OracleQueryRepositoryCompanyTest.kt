package com.payroll.web.adapter.persistence.repository.queryrepository

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.springtestdbunit.assertion.DatabaseAssertionMode
import com.payroll.web.central.query.model.QueryModelCompany
import com.payroll.web.central.query.repository.QueryRepositoryCompany
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@RunWith(SpringRunner::class)
@TestExecutionListeners(
        DependencyInjectionTestExecutionListener::class,
        TransactionDbUnitTestExecutionListener::class
)
@Transactional
class OracleQueryRepositoryCompanyTest {

    @Autowired
    lateinit var queryRepositoryCompany: QueryRepositoryCompany

    @Test
    @DatabaseSetup(value = ["/dbunit/setup/OracleQueryRepositoryCompanyTest/findQueryModelCompanyByIdTest.xml"])
    @ExpectedDatabase(
            value = "/dbunit/expect/OracleQueryRepositoryCompanyTest/findQueryModelCompanyByIdTest.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED
    )
    fun findQueryModelCompanyByIdTest() {
        // setup
        val expectQueryModelCompany = QueryModelCompany(
                companyId = 1L,
                companyName = "Aテスト株式会社",
                representatives = listOf("A タロウ", "A ジロウ")
        )

        // execute and verify
        val actualQueryModelCompany = queryRepositoryCompany.findQueryModelCompanyById(1L)
        assertThat(actualQueryModelCompany).isEqualTo(expectQueryModelCompany)
    }
}