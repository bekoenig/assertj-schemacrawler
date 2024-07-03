package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Index;
import schemacrawler.schema.IndexColumn;
import schemacrawler.schema.IndexColumnSortSequence;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class AbstractIndexColumnAssert<SELF extends AbstractIndexColumnAssert<SELF>>
        extends AbstractKeyColumnAssert<SELF, IndexColumn> {

    AbstractIndexColumnAssert(IndexColumn actual, Class<?> selfType) {
        super(actual, selfType);
    }

    @SafeVarargs
    public final SELF satisfiesIndex(Consumer<Index>... requirements) {
        extracting(IndexColumn::getIndex).satisfies(requirements);
        return myself;
    }

    public SELF matchesIndexOrdinalPosition(Predicate<Integer> predicate) {
        extracting(IndexColumn::getIndexOrdinalPosition).matches(predicate);
        return myself;
    }

    @SafeVarargs
    public final SELF satisfiesSortSequence(Consumer<IndexColumnSortSequence>... requirements) {
        extracting(IndexColumn::getSortSequence).satisfies(requirements);
        return myself;
    }

}
