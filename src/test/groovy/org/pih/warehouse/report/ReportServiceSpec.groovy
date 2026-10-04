package org.pih.warehouse.report

import grails.testing.services.ServiceUnitTest
import org.pih.warehouse.inventory.Transaction
import org.pih.warehouse.inventory.TransactionEntry
import spock.lang.Specification

class ReportServiceSpec extends Specification implements ServiceUnitTest<ReportService> {

    void "backdated correction reuses the already filtered product entries"() {
        given:
        Date reportDate = new Date().clearTime()

        Transaction backdatedTransaction = new Transaction(
                transactionDate: reportDate - 5,
                dateCreated: reportDate - 2
        )
        Transaction onTimeTransaction = new Transaction(
                transactionDate: reportDate - 2,
                dateCreated: reportDate - 2
        )

        TransactionEntry backdatedEntry = new TransactionEntry(
                transaction: backdatedTransaction,
                quantity: 4
        )
        TransactionEntry onTimeEntry = new TransactionEntry(
                transaction: onTimeTransaction,
                quantity: 7
        )

        when:
        List<TransactionEntry> result =
                service.getBackdatedTransactionEntries([backdatedEntry, onTimeEntry])

        then:
        result == [backdatedEntry]
    }
}
