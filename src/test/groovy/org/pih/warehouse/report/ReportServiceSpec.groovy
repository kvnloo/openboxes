package org.pih.warehouse.report

import grails.testing.services.ServiceUnitTest
import org.pih.warehouse.data.DataService
import spock.lang.Specification

class ReportServiceSpec extends Specification implements ServiceUnitTest<ReportService> {

    void 'refreshProductDemandData prepares replacement before atomically swapping live table'() {
        given:
        DataService dataService = Mock()
        service.dataService = dataService
        List<String> statements = []

        when:
        service.refreshProductDemandData()

        then:
        1 * dataService.executeStatementsFailFast(_ as List) >> { List<String> value ->
            statements = value
        }

        and:
        statements.find { it.contains('CREATE TABLE product_demand_details_tmp AS') }
        statements.find { it.contains('ALTER TABLE product_demand_details_tmp ADD INDEX') }
        statements.find { it.contains('CREATE TABLE IF NOT EXISTS product_demand_details LIKE product_demand_details_tmp') }

        and: 'the old live table is never dropped before the replacement is complete'
        !statements.any { it == 'DROP TABLE IF EXISTS product_demand_details;' }
        int renameIndex = statements.findIndexOf { it.contains('RENAME TABLE') }
        int buildIndex = statements.findIndexOf { it.contains('CREATE TABLE product_demand_details_tmp AS') }
        int indexIndex = statements.findIndexOf { it.contains('ALTER TABLE product_demand_details_tmp ADD INDEX') }
        renameIndex > buildIndex
        renameIndex > indexIndex

        and: 'one multi-table rename performs the live swap'
        statements[renameIndex].contains('product_demand_details TO product_demand_details_old')
        statements[renameIndex].contains('product_demand_details_tmp TO product_demand_details')
        statements.last() == 'DROP TABLE product_demand_details_old;'
    }
}
