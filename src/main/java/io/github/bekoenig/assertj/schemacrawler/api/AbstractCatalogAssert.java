package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.*;
import schemacrawler.schema.*;

import java.util.Collection;
import java.util.List;
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

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert>>
    ListableNamedObjectAssert<MY_SELF, List<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert> columnDataTypes() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getColumnDataTypes(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert>>
    ListableNamedObjectAssert<MY_SELF, List<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert> columnDataTypes(String schemaName) {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getColumnDataTypes(getSchema(schemaName)),
                SchemaCrawlerAssertions::assertThat);
    }

    public ObjectAssert<CrawlInfo> crawlInfo() {
        return extracting(Catalog::getCrawlInfo)
                .asInstanceOf(InstanceOfAssertFactories.type(CrawlInfo.class));
    }

    public ObjectAssert<DatabaseInfo> databaseInfo() {
        return extracting(Catalog::getDatabaseInfo)
                .asInstanceOf(InstanceOfAssertFactories.type(DatabaseInfo.class));
    }

    public AbstractCollectionAssert<?, Collection<? extends DatabaseUser>, DatabaseUser, ObjectAssert<DatabaseUser>> databaseUsers() {
        return extracting(Catalog::getDatabaseUsers)
                .asInstanceOf(InstanceOfAssertFactories.collection(DatabaseUser.class));
    }

    public ObjectAssert<JdbcDriverInfo> jdbcDriverInfo() {
        return extracting(Catalog::getJdbcDriverInfo)
                .asInstanceOf(InstanceOfAssertFactories.type(JdbcDriverInfo.class));
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Routine>, Routine, RoutineAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Routine>, Routine, RoutineAssert> routines() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getRoutines(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Routine>, Routine, RoutineAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Routine>, Routine, RoutineAssert> routines(String schemaName) {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getRoutines(getSchema(schemaName)),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Schema>, Schema, SchemaAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Schema>, Schema, SchemaAssert> schemas() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getSchemas(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Sequence>, Sequence, SequenceAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Sequence>, Sequence, SequenceAssert> sequences() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getSequences(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Sequence>, Sequence, SequenceAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Sequence>, Sequence, SequenceAssert> sequences(String schemaName) {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getSequences(getSchema(schemaName)),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Synonym>, Synonym, SynonymAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Synonym>, Synonym, SynonymAssert> synonyms() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getSynonyms(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Synonym>, Synonym, SynonymAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Synonym>, Synonym, SynonymAssert> synonyms(String schemaName) {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getSynonyms(getSchema(schemaName)),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert>>
    ListableNamedObjectAssert<MY_SELF, List<ColumnDataType>, ColumnDataType, ColumnDataTypeAssert> systemColumnDataTypes() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getSystemColumnDataTypes(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert> tables() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getTables(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert> tables(String schemaName) {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getTables(getSchema(schemaName)),
                SchemaCrawlerAssertions::assertThat);
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
