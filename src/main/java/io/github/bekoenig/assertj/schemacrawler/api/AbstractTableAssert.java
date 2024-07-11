package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.*;
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

    public FactoryBasedNavigableIterableAssert<?, Collection<PrimaryKey>, PrimaryKey, PrimaryKeyAssert> alternateKeys() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getAlternateKeys(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableListAssert<?, List<Column>, Column, ColumnAssert> columns() {
        isNotNull();
        return new FactoryBasedNavigableListAssert<>(
                actual.getColumns(),
                FactoryBasedNavigableListAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(DefinedObject::getDefinition).matches(predicate);
        return myself;
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<ForeignKey>, ForeignKey, ForeignKeyAssert> exportedForeignKeys() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getExportedForeignKeys(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<ForeignKey>, ForeignKey, ForeignKeyAssert> foreignKeys() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getForeignKeys(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Column>, Column, ColumnAssert> hiddenColumns() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getHiddenColumns(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<ForeignKey>, ForeignKey, ForeignKeyAssert> importedForeignKeys() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getImportedForeignKeys(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Index>, Index, IndexAssert> indexes() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getIndexes(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public PrimaryKeyAssert primaryKey() {
        return extracting(Table::getPrimaryKey)
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.primaryKey());
    }

    public FactoryBasedNavigableIterableAssert<?, Iterable<? extends Privilege<?>>, Privilege<?>, PrivilegeAssert> privileges() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getPrivileges(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Table>, Table, TableAssert> relatedTables(
            TableRelationshipType tableRelationshipType) {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getRelatedTables(tableRelationshipType),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<TableConstraint>, TableConstraint, TableConstraintAssert> tableConstraints() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getTableConstraints(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    @SafeVarargs
    public final SELF satisfiesTableType(Consumer<TableType>... requirements) {
        extracting(Table::getTableType).satisfies(requirements);
        return myself;
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<Trigger>, Trigger, TriggerAssert> triggers() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getTriggers(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    @SafeVarargs
    public final SELF satisfiesType(Consumer<TableType>... requirements) {
        extracting(Table::getType).satisfies(requirements);
        return myself;
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<WeakAssociation>, TableReference, TableReferenceAssert> weakAssociation() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getWeakAssociations(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
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
        return extracting(x -> x.lookupAlternateKey(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.primaryKey());
    }

    public ColumnAssert column(String name) {
        return extracting(x -> x.lookupColumn(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.column());
    }

    public ForeignKeyAssert foreignKey(String name) {
        return extracting(x -> x.lookupForeignKey(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.foreignKey());
    }

    public IndexAssert index(String name) {
        return extracting(x -> x.lookupIndex(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.index());
    }

    public PrivilegeAssert privilege(String name) {
        return extracting(x -> x.lookupPrivilege(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.privilege());
    }

    public TableConstraintAssert tableConstraint(String name) {
        return extracting(x -> x.lookupTableConstraint(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.tableConstraint());
    }

    public TriggerAssert trigger(String name) {
        return extracting(x -> x.lookupTrigger(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.trigger());
    }

}
