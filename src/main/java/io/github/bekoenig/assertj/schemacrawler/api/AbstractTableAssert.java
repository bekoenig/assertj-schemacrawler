package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractTableAssert<
        SELF extends AbstractTableAssert<SELF, ACTUAL>,
        ACTUAL extends Table>
        extends AbstractDatabaseObjectAssert<SELF, ACTUAL> {

    protected AbstractTableAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<PrimaryKey>, PrimaryKey, PrimaryKeyAssert>>
    ListableNamedObjectAssert<MY_SELF, List<PrimaryKey>, PrimaryKey, PrimaryKeyAssert> alternateKeys() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getAlternateKeys(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Column>, Column, ColumnAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Column>, Column, ColumnAssert> columns() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getColumns(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(DefinedObject::getDefinition).matches(predicate);
        return myself;
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<ForeignKey>, ForeignKey, ForeignKeyAssert>>
    ListableNamedObjectAssert<MY_SELF, List<ForeignKey>, ForeignKey, ForeignKeyAssert> exportedForeignKeys() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getExportedForeignKeys(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<ForeignKey>, ForeignKey, ForeignKeyAssert>>
    ListableNamedObjectAssert<MY_SELF, List<ForeignKey>, ForeignKey, ForeignKeyAssert> foreignKeys() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getForeignKeys(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Column>, Column, ColumnAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Column>, Column, ColumnAssert> hiddenColumns() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getHiddenColumns(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<ForeignKey>, ForeignKey, ForeignKeyAssert>>
    ListableNamedObjectAssert<MY_SELF, List<ForeignKey>, ForeignKey, ForeignKeyAssert> importedForeignKeys() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getImportedForeignKeys(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Index>, Index, IndexAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Index>, Index, IndexAssert> indexes() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getIndexes(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF satisfiesPrimaryKey(Consumer<PrimaryKey> requirement) {
        extracting(Table::getPrimaryKey)
                .satisfies(requirement);
        return myself;
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Privilege<Table>>, Privilege<Table>, PrivilegeAssert<Table>>>
    ListableNamedObjectAssert<MY_SELF, List<Privilege<Table>>, Privilege<Table>, PrivilegeAssert<Table>> privileges() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getPrivileges(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert> relatedTables(
            TableRelationshipType tableRelationshipType) {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getRelatedTables(tableRelationshipType),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<TableConstraint>, TableConstraint, TableConstraintAssert>>
    ListableNamedObjectAssert<MY_SELF, List<TableConstraint>, TableConstraint, TableConstraintAssert> tableConstraints() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getTableConstraints(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF satisfiesTableType(Consumer<TableType> requirement) {
        extracting(Table::getTableType).satisfies(requirement);
        return myself;
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Trigger>, Trigger, TriggerAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Trigger>, Trigger, TriggerAssert> triggers() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getTriggers(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF satisfiesType(Consumer<TableType> requirement) {
        extracting(Table::getType).satisfies(requirement);
        return myself;
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<TableReference>, TableReference, TableReferenceAssert>>
    ListableNamedObjectAssert<MY_SELF, List<TableReference>, TableReference, TableReferenceAssert> weakAssociation() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getWeakAssociations(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF hasDefinition(boolean expected) {
        return returns(expected, DefinedObject::hasDefinition);
    }

    public SELF hasForeignKeys(boolean expected) {
        return returns(expected, Table::hasForeignKeys);
    }

    public SELF hasPrimaryKey(boolean expected) {
        return returns(expected, Table::hasPrimaryKey);
    }

    public PrimaryKeyAssert alternateKey(String name) {
        return extracting(x -> x.lookupAlternateKey(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.primaryKey());
    }

    public ColumnAssert column(String name) {
        return extracting(x -> x.lookupColumn(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.column());
    }

    public ForeignKeyAssert foreignKey(String name) {
        return extracting(x -> x.lookupForeignKey(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.foreignKey());
    }

    public IndexAssert index(String name) {
        return extracting(x -> x.lookupIndex(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.index());
    }

    @SuppressWarnings("unchecked")
    public PrivilegeAssert<Table> privilege(String name) {
        return (PrivilegeAssert<Table>) extracting(x -> x.lookupPrivilege(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.privilege());
    }

    public TableConstraintAssert tableConstraint(String name) {
        return extracting(x -> x.lookupTableConstraint(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.tableConstraint());
    }

    public TriggerAssert trigger(String name) {
        return extracting(x -> x.lookupTrigger(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.trigger());
    }

}
