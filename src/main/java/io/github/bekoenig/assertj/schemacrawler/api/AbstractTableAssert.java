package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractCollectionAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import schemacrawler.schema.*;

import java.util.Collection;
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

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert> dependentTables() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getDependentTables(),
                SchemaCrawlerAssertions::assertThat);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert>>
    ListableNamedObjectAssert<MY_SELF, List<Table>, Table, TableAssert> referencedTables() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getReferencedTables(),
                SchemaCrawlerAssertions::assertThat);
    }

    public AbstractCollectionAssert<?, Collection<? extends DatabaseObject>, DatabaseObject, ObjectAssert<DatabaseObject>> referencedObjects() {
        return extracting(Table::getReferencedObjects, Assertions::assertThat);
    }

    public AbstractCollectionAssert<?, Collection<? extends DatabaseObject>, DatabaseObject, ObjectAssert<DatabaseObject>> usedByObjects() {
        return extracting(Table::getUsedByObjects, Assertions::assertThat);
    }

    public AbstractCollectionAssert<?, Collection<? extends WeakAssociation>, WeakAssociation, ObjectAssert<WeakAssociation>> weakAssociations() {
        return extracting(Table::getWeakAssociations, Assertions::assertThat);
    }

    public SELF isSelfReferencing(boolean expected) {
        return returns(expected, Table::isSelfReferencing);
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
        return extracting(x -> x.lookupAlternateKey(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

    public ColumnAssert column(String name) {
        return extracting(x -> x.lookupColumn(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

    public ForeignKeyAssert foreignKey(String name) {
        return extracting(x -> x.lookupForeignKey(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

    public IndexAssert index(String name) {
        return extracting(x -> x.lookupIndex(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

    public PrivilegeAssert<Table> privilege(String name) {
        return extracting(x -> x.lookupPrivilege(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

    public TableConstraintAssert tableConstraint(String name) {
        return extracting(x -> x.lookupTableConstraint(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

    public TriggerAssert trigger(String name) {
        return extracting(x -> x.lookupTrigger(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

}
