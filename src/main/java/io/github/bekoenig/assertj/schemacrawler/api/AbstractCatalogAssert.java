package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.*;
import schemacrawler.schema.*;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractCatalogAssert<SELF extends AbstractCatalogAssert<SELF>>
        extends AbstractComparableAssert<SELF, Catalog> {

    protected AbstractCatalogAssert(Catalog actual, Class<?> selfType) {
        super(actual, selfType);
    }

    private Schema getSchema(String schemaName) {
        Optional<Schema> schema = actual.lookupSchema(schemaName);
        assertThat(schema).as("Undefined schema name '%s'", schemaName).isPresent();
        return schema.get();
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert> columnDataTypes() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getColumnDataTypes(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert> columnDataTypes(
            String schemaName) {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getColumnDataTypes(getSchema(schemaName)),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public ObjectAssert<CrawlInfo> crawlInfo() {
        return extracting(Catalog::getCrawlInfo)
                .asInstanceOf(InstanceOfAssertFactories.type(CrawlInfo.class));
    }

    public ObjectAssert<CrawlInfo> databaseInfo() {
        return extracting(Catalog::getDatabaseInfo)
                .asInstanceOf(InstanceOfAssertFactories.type(CrawlInfo.class));
    }

    public AbstractCollectionAssert<?, Collection<? extends DatabaseUser>, DatabaseUser, ObjectAssert<DatabaseUser>> databaseUsers() {
        return extracting(Catalog::getDatabaseUsers)
                .asInstanceOf(InstanceOfAssertFactories.collection(DatabaseUser.class));
    }

    public ObjectAssert<CrawlInfo> jdbcDriverInfo() {
        return extracting(Catalog::getJdbcDriverInfo)
                .asInstanceOf(InstanceOfAssertFactories.type(CrawlInfo.class));
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Routine>, Routine, RoutineAssert> routines() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getRoutines(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Routine>, Routine, RoutineAssert> routines(
            String schemaName) {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getRoutines(getSchema(schemaName)),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Schema>, Schema, SchemaAssert> schemas() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getSchemas(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Sequence>, Sequence, SequenceAssert> sequences() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getSequences(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Sequence>, Sequence, SequenceAssert> sequences(
            String schemaName) {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getSequences(getSchema(schemaName)),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Synonym>, Synonym, SynonymAssert> synonyms() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getSynonyms(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Synonym>, Synonym, SynonymAssert> synonyms(
            String schemaName) {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getSynonyms(getSchema(schemaName)),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert> systemColumnDataTypes() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getSystemColumnDataTypes(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Table>, Table, TableAssert> tables() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getTables(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Table>, Table, TableAssert> tables(String schemaName) {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getTables(getSchema(schemaName)),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public ColumnAssert column(String schemaName, String tableName, String name) {
        return extracting(x -> x.lookupColumn(getSchema(schemaName), tableName, name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.column());
    }

    public ColumnDataTypeAssert columnDataType(String schemaName, String name) {
        return extracting(x -> x.lookupColumnDataType(getSchema(schemaName), name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.columnDataType());
    }

    public SchemaReferenceAssert schema(String schemaName) {
        return extracting(x -> x.lookupSchema(schemaName).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.schemaReference());
    }

    public SequenceAssert sequence(String schemaName, String name) {
        return extracting(x -> x.lookupSequence(getSchema(schemaName), name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.sequence());
    }

    public SynonymAssert synonym(String schemaName, String name) {
        return extracting(x -> x.lookupSynonym(getSchema(schemaName), name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.synonym());
    }

    public ColumnDataTypeAssert systemColumnDataType(String name) {
        return extracting(x -> x.lookupSystemColumnDataType(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.columnDataType());
    }

    public TableAssert table(String schemaName, String name) {
        return extracting(x -> x.lookupTable(getSchema(schemaName), name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.table());
    }

}
