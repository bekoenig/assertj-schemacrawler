package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.InstanceOfAssertFactory;
import schemacrawler.schema.*;
import schemacrawler.schemacrawler.SchemaReference;

public interface SchemaCrawlerInstanceOfAssertFactories {

    static InstanceOfAssertFactory<Catalog, CatalogAssert> catalog() {
        return new InstanceOfAssertFactory<>(Catalog.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<Column, ColumnAssert> column() {
        return new InstanceOfAssertFactory<>(Column.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<ColumnDataType, ColumnDataTypeAssert> columnDataType() {
        return new InstanceOfAssertFactory<>(ColumnDataType.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<ColumnReference, ColumnReferenceAssert> columnReferenceAssert() {
        return new InstanceOfAssertFactory<>(ColumnReference.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<ForeignKey, ForeignKeyAssert> foreignKey() {
        return new InstanceOfAssertFactory<>(ForeignKey.class, SchemaCrawlerAssertions::assertThat);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    static <D extends DatabaseObject> InstanceOfAssertFactory<Grant, GrantAssert<D>> grant(Class<D> databaseObjectType) {
        return new InstanceOfAssertFactory<>(Grant.class, new Class[]{databaseObjectType},
                actual -> SchemaCrawlerAssertions.assertThat(actual, databaseObjectType));
    }

    static InstanceOfAssertFactory<Index, IndexAssert> index() {
        return new InstanceOfAssertFactory<>(Index.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<IndexColumn, IndexColumnAssert> indexColumn() {
        return new InstanceOfAssertFactory<>(IndexColumn.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<PrimaryKey, PrimaryKeyAssert> primaryKey() {
        return new InstanceOfAssertFactory<>(PrimaryKey.class, SchemaCrawlerAssertions::assertThat);
    }

    @SuppressWarnings("rawtypes")
    static <D extends DatabaseObject> InstanceOfAssertFactory<Privilege, PrivilegeAssert<D>> privilege(Class<D> databaseObjectType) {
        return new InstanceOfAssertFactory<>(Privilege.class, new Class[]{databaseObjectType}, SchemaCrawlerAssertions::<D>assertThat);
    }

    @SuppressWarnings("rawtypes")
    static <R extends Routine> InstanceOfAssertFactory<RoutineParameter, RoutineParameterAssert<R>> routineParameter() {
        return new InstanceOfAssertFactory<>(RoutineParameter.class, SchemaCrawlerAssertions::<R>assertThat);
    }

    static InstanceOfAssertFactory<Routine, RoutineAssert> routine() {
        return new InstanceOfAssertFactory<>(Routine.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<Schema, SchemaAssert> schema() {
        return new InstanceOfAssertFactory<>(Schema.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<SchemaReference, SchemaReferenceAssert> schemaReference() {
        return new InstanceOfAssertFactory<>(SchemaReference.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<Sequence, SequenceAssert> sequence() {
        return new InstanceOfAssertFactory<>(Sequence.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<Synonym, SynonymAssert> synonym() {
        return new InstanceOfAssertFactory<>(Synonym.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<Table, TableAssert> table() {
        return new InstanceOfAssertFactory<>(Table.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<TableConstraint, TableConstraintAssert> tableConstraint() {
        return new InstanceOfAssertFactory<>(TableConstraint.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<TableConstraintColumn, TableConstraintColumnAssert> tableConstraintColumn() {
        return new InstanceOfAssertFactory<>(TableConstraintColumn.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<TableReference, TableReferenceAssert> tableReference() {
        return new InstanceOfAssertFactory<>(TableReference.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<Trigger, TriggerAssert> trigger() {
        return new InstanceOfAssertFactory<>(Trigger.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<View, ViewAssert> view() {
        return new InstanceOfAssertFactory<>(View.class, SchemaCrawlerAssertions::assertThat);
    }

    static InstanceOfAssertFactory<WeakAssociation, WeakAssociationAssert> weakAssociation() {
        return new InstanceOfAssertFactory<>(WeakAssociation.class, SchemaCrawlerAssertions::assertThat);
    }

}
