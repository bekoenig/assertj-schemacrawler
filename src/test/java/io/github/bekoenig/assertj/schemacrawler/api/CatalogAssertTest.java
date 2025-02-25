package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.CrawlInfo;
import schemacrawler.schema.DatabaseInfo;
import schemacrawler.schema.JdbcDriverInfo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatalogAssertTest {

    @Test
    void crawlInfo() {
        // GIVEN
        Catalog catalog = mock();
        CrawlInfo crawlInfo = mock();
        when(catalog.getCrawlInfo()).thenReturn(crawlInfo);
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ObjectAssert<CrawlInfo> crawlInfoObjectAssert = catalogAssert.crawlInfo();

        // THEN
        crawlInfoObjectAssert.isEqualTo(crawlInfo);
    }

    @Test
    void databaseInfo() {
        // GIVEN
        Catalog catalog = mock();
        DatabaseInfo databaseInfo = mock();
        when(catalog.getDatabaseInfo()).thenReturn(databaseInfo);
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ObjectAssert<DatabaseInfo> databaseInfoObjectAssert = catalogAssert.databaseInfo();

        // THEN
        databaseInfoObjectAssert.isEqualTo(databaseInfo);
    }

    @Test
    void jdbcDriverInfo() {
        // GIVEN
        Catalog catalog = mock();
        JdbcDriverInfo jdbcDriverInfo = mock();
        when(catalog.getJdbcDriverInfo()).thenReturn(jdbcDriverInfo);
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ObjectAssert<JdbcDriverInfo> jdbcDriverInfoObjectAssert = catalogAssert.jdbcDriverInfo();

        // THEN
        jdbcDriverInfoObjectAssert.isEqualTo(jdbcDriverInfo);
    }
}