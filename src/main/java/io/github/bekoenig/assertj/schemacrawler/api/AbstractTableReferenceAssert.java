package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.FactoryBasedNavigableListAssert;
import schemacrawler.schema.ColumnReference;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableReference;

import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractTableReferenceAssert<
        SELF extends AbstractTableConstraintAssert<SELF, ACTUAL>,
        ACTUAL extends TableReference>
        extends AbstractTableConstraintAssert<SELF, ACTUAL> {

    protected AbstractTableReferenceAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public <MY_SELF extends FactoryBasedNavigableListAssert<MY_SELF, List<ColumnReference>, ColumnReference, ColumnReferenceAssert>>
    FactoryBasedNavigableListAssert<MY_SELF, List<ColumnReference>, ColumnReference, ColumnReferenceAssert> columnReferences() {
        return new FactoryBasedNavigableListAssert<>(
                actual.getColumnReferences(),
                FactoryBasedNavigableListAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public SELF satisfiesForeignKeyTable(Consumer<Table> requirement) {
        extracting(TableReference::getForeignKeyTable).satisfies(requirement);
        return myself;
    }

    public SELF satisfiesPrimaryKeyTable(Consumer<Table> requirement) {
        extracting(TableReference::getPrimaryKeyTable).satisfies(requirement);
        return myself;
    }

}
