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

    public FactoryBasedNavigableListAssert<?, List<ColumnReference>, ColumnReference, ColumnReferenceAssert> columnReferences() {
        return new FactoryBasedNavigableListAssert<>(actual.getColumnReferences(), FactoryBasedNavigableListAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    @SafeVarargs
    public final SELF satisfiesForeignKeyTable(Consumer<Table>... requirements) {
        extracting(TableReference::getForeignKeyTable).satisfies(requirements);
        return myself;
    }

    @SafeVarargs
    public final SELF satisfiesPrimaryKeyTable(Consumer<Table>... requirements) {
        extracting(TableReference::getPrimaryKeyTable).satisfies(requirements);
        return myself;
    }

}
