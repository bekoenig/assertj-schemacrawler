package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import schemacrawler.schema.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatalogAssertTest {

    @Test
    void column() {
        // GIVEN
        Catalog catalog = mock();
        Column column = mock();
        when(column.getName()).thenReturn("ONE_COLUMN");
        when(catalog.lookupColumn(any(), any(), any())).thenReturn(Optional.of(column));
        Optional<Schema> schema = Optional.of(mock());
        when(catalog.lookupSchema(any())).thenReturn(schema);
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ColumnAssert columnAssert = catalogAssert.column("SOME_SCHEMA", "ANY_TABLE", "ONE_COLUMN");

        // THEN
        columnAssert.isNotNull().isEqualTo(column).hasName("ONE_COLUMN");
    }

    @Test
    void column_null() {
        // GIVEN
        Catalog catalog = mock();
        when(catalog.lookupColumn(any(), any(), any())).thenReturn(Optional.empty());
        Optional<Schema> schema = Optional.of(mock());
        when(catalog.lookupSchema(any())).thenReturn(schema);
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ColumnAssert columnAssert = catalogAssert.column("SOME_SCHEMA", "ANY_TABLE", "ONE_COLUMN");

        // THEN
        columnAssert.isNull();
    }

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
    void crawlInfo_null() {
        // GIVEN
        Catalog catalog = mock();
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ObjectAssert<CrawlInfo> crawlInfoObjectAssert = catalogAssert.crawlInfo();

        // THEN
        crawlInfoObjectAssert.isNull();
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
    void databaseInfo_null() {
        // GIVEN
        Catalog catalog = mock();
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ObjectAssert<DatabaseInfo> databaseInfoObjectAssert = catalogAssert.databaseInfo();

        // THEN
        databaseInfoObjectAssert.isNull();
    }

    @Test
    void databaseUsers_empty() {
        // GIVEN
        Catalog catalog = mock();
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        var databaseUsersAssert = catalogAssert.databaseUsers();

        // THEN
        databaseUsersAssert.hasSize(0);
    }

    @Test
    void databaseUsers_elements() {
        // GIVEN
        Catalog catalog = mock();
        DatabaseUser databaseUser = mock();
        when(catalog.getDatabaseUsers()).thenReturn(List.of(databaseUser));
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        var databaseUsersAssert = catalogAssert.databaseUsers();

        // THEN
        databaseUsersAssert.hasSize(1).containsExactly(databaseUser);
    }

    @Test
    void databaseUsers_null() {
        // GIVEN
        Catalog catalog = mock();
        when(catalog.getDatabaseUsers()).thenReturn(null);
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        var databaseUsersAssert = catalogAssert.databaseUsers();

        // THEN
        databaseUsersAssert.isNull();
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

    @Test
    void jdbcDriverInfo_null() {
        // GIVEN
        Catalog catalog = mock();
        CatalogAssert catalogAssert = SchemaCrawlerAssertions.assertThat(catalog);

        // WHEN
        ObjectAssert<JdbcDriverInfo> jdbcDriverInfoObjectAssert = catalogAssert.jdbcDriverInfo();

        // THEN
        jdbcDriverInfoObjectAssert.isNull();
    }
}