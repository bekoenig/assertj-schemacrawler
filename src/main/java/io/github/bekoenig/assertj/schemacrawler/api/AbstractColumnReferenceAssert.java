package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractObjectAssert;
import schemacrawler.schema.ColumnReference;

import java.util.function.Predicate;

public class AbstractColumnReferenceAssert<SELF extends AbstractColumnReferenceAssert<SELF>> extends AbstractObjectAssert<SELF, ColumnReference> {

    public AbstractColumnReferenceAssert(ColumnReference columnReference, Class<?> selfType) {
        super(columnReference, selfType);
    }

    public ColumnAssert foreignKeyColumn() {
        return extracting(ColumnReference::getForeignKeyColumn)
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.column());
    }

    public SELF matchesKeySequence(Predicate<Integer> predicate) {
        extracting(ColumnReference::getKeySequence)
                .matches(predicate);
        return myself;
    }

    public ColumnAssert primaryKeyColumn() {
        return extracting(ColumnReference::getPrimaryKeyColumn)
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.column());
    }

}
