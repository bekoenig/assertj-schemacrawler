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

    public AbstractCollectionAssert<?, Collection<? extends PrimaryKey>, PrimaryKey, ObjectAssert<PrimaryKey>> alternateKeys() {
        return extracting(Table::getAlternateKeys)
                .asInstanceOf(InstanceOfAssertFactories.collection(PrimaryKey.class));
    }

    public ClassBasedNavigableListAssert<?, List<Column>, Column, ColumnAssert> columns() {
        isNotNull();
        return new ClassBasedNavigableListAssert<>(actual.getColumns(), ColumnAssert.class);
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(DefinedObject::getDefinition).matches(predicate);
        return myself;
    }

    public ClassBasedNavigableIterableAssert<?, Collection<ForeignKey>, ForeignKey, ForeignKeyAssert> exportedForeignKeys() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getExportedForeignKeys(),
                ClassBasedNavigableIterableAssert.class, ForeignKeyAssert.class);
    }

    public ClassBasedNavigableIterableAssert<?, Collection<ForeignKey>, ForeignKey, ForeignKeyAssert> foreignKeys() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getForeignKeys(), ClassBasedNavigableIterableAssert.class,
                ForeignKeyAssert.class);
    }

    public ClassBasedNavigableIterableAssert<?, Collection<Column>, Column, ColumnAssert> hiddenColumns() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getHiddenColumns(),
                ClassBasedNavigableIterableAssert.class, ColumnAssert.class);
    }

    public ClassBasedNavigableIterableAssert<?, Collection<ForeignKey>, ForeignKey, ForeignKeyAssert> importedForeignKeys() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getImportedForeignKeys(),
                ClassBasedNavigableIterableAssert.class, ForeignKeyAssert.class);
    }

    public ClassBasedNavigableIterableAssert<?, Collection<Index>, Index, IndexAssert> indexes() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getIndexes(), ClassBasedNavigableIterableAssert.class,
                IndexAssert.class);
    }

    public PrimaryKeyAssert primaryKey() {
        return extracting(Table::getPrimaryKey)
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.primaryKey());
    }

    public FactoryBasedNavigableIterableAssert<?, Iterable<? extends Privilege<Table>>, Privilege<Table>, PrivilegeAssert<Table>> privileges() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(actual.getPrivileges(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public ClassBasedNavigableIterableAssert<?, Collection<Table>, Table, TableAssert> relatedTables(
            TableRelationshipType tableRelationshipType) {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getRelatedTables(tableRelationshipType),
                ClassBasedNavigableIterableAssert.class, TableAssert.class);
    }

    public ClassBasedNavigableIterableAssert<?, Collection<TableConstraint>, TableConstraint, TableConstraintAssert> tableConstraints() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getTableConstraints(),
                ClassBasedNavigableIterableAssert.class, TableConstraintAssert.class);
    }

    @SafeVarargs
    public final SELF satisfiesTableType(Consumer<TableType>... requirements) {
        extracting(Table::getTableType).satisfies(requirements);
        return myself;
    }

    public ClassBasedNavigableIterableAssert<?, Collection<Trigger>, Trigger, TriggerAssert> triggers() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getTriggers(), ClassBasedNavigableIterableAssert.class,
                TriggerAssert.class);
    }

    @SafeVarargs
    public final SELF satisfiesType(Consumer<TableType>... requirements) {
        extracting(Table::getType).satisfies(requirements);
        return myself;
    }

    public ClassBasedNavigableIterableAssert<?, Collection<WeakAssociation>, TableReference, TableReferenceAssert> weakAssociation() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getWeakAssociations(),
                ClassBasedNavigableIterableAssert.class, TableReferenceAssert.class);
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

    public PrivilegeAssert<? extends Table> privilege(String name) {
        return extracting(x -> x.lookupPrivilege(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.privilege(Table.class));
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
