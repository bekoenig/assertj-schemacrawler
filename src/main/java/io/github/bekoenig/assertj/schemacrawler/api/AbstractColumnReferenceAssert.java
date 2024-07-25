package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractComparableAssert;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnReference;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class AbstractColumnReferenceAssert<SELF extends AbstractColumnReferenceAssert<SELF>> extends AbstractComparableAssert<SELF, ColumnReference> {

    public AbstractColumnReferenceAssert(ColumnReference actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesForeignKeyColumn(Consumer<Column> requirement) {
        extracting(ColumnReference::getForeignKeyColumn)
                .satisfies(requirement);
        return myself;
    }

    public SELF matchesKeySequence(Predicate<Integer> predicate) {
        extracting(ColumnReference::getKeySequence)
                .matches(predicate);
        return myself;
    }

    public SELF satisfiesPrimaryKeyColumn(Consumer<Column> requirement) {
        extracting(ColumnReference::getPrimaryKeyColumn)
                .satisfies(requirement);
        return myself;
    }

}
