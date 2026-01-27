package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.*;
import schemacrawler.schemacrawler.SchemaReference;

public class SchemaCrawlerAssertions {

    public static CatalogAssert assertThat(Catalog actual) {
        return new CatalogAssert(actual);
    }

    public static ColumnAssert assertThat(Column actual) {
        return new ColumnAssert(actual);
    }

    public static ColumnDataTypeAssert assertThat(ColumnDataType actual) {
        return new ColumnDataTypeAssert(actual);
    }

    public static ColumnReferenceAssert assertThat(ColumnReference actual) {
        return new ColumnReferenceAssert(actual);
    }

    public static ForeignKeyAssert assertThat(ForeignKey actual) {
        return new ForeignKeyAssert(actual);
    }

    public static <PARENT extends DatabaseObject> GrantAssert<PARENT> assertThat(Grant<PARENT> actual) {
        return new GrantAssert<>(actual);
    }

    public static IndexAssert assertThat(Index actual) {
        return new IndexAssert(actual);
    }

    public static IndexColumnAssert assertThat(IndexColumn actual) {
        return new IndexColumnAssert(actual);
    }

    public static PrimaryKeyAssert assertThat(PrimaryKey actual) {
        return new PrimaryKeyAssert(actual);
    }

    public static <PARENT extends DatabaseObject> PrivilegeAssert<PARENT> assertThat(Privilege<PARENT> actual) {
        return new PrivilegeAssert<>(actual);
    }

    public static RoutineAssert assertThat(Routine actual) {
        return new RoutineAssert(actual);
    }

    public static <ACTUAL extends RoutineParameter<PARENT>, PARENT extends Routine> RoutineParameterAssert<ACTUAL, PARENT> assertThat(ACTUAL actual) {
        return new RoutineParameterAssert<>(actual);
    }

    public static SchemaAssert assertThat(Schema actual) {
        return new SchemaAssert(actual);
    }

    public static SchemaReferenceAssert assertThat(SchemaReference actual) {
        return new SchemaReferenceAssert(actual);
    }

    public static SequenceAssert assertThat(Sequence actual) {
        return new SequenceAssert(actual);
    }

    public static SynonymAssert assertThat(Synonym actual) {
        return new SynonymAssert(actual);
    }

    public static TableAssert assertThat(Table actual) {
        return new TableAssert(actual);
    }

    public static TableConstraintAssert assertThat(TableConstraint actual) {
        return new TableConstraintAssert(actual);
    }

    public static TableConstraintColumnAssert assertThat(TableConstraintColumn actual) {
        return new TableConstraintColumnAssert(actual);
    }

    public static TableReferenceAssert assertThat(TableReference actual) {
        return new TableReferenceAssert(actual);
    }

    public static TriggerAssert assertThat(Trigger actual) {
        return new TriggerAssert(actual);
    }

    public static ViewAssert assertThat(View actual) {
        return new ViewAssert(actual);
    }

}
