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

    public SELF satisfiesIndex(Consumer<Index> requirement) {
        extracting(IndexColumn::getIndex).satisfies(requirement);
        return myself;
    }

    public SELF matchesIndexOrdinalPosition(Predicate<Integer> predicate) {
        extracting(IndexColumn::getIndexOrdinalPosition).matches(predicate);
        return myself;
    }

    public SELF satisfiesSortSequence(Consumer<IndexColumnSortSequence> requirement) {
        extracting(IndexColumn::getSortSequence).satisfies(requirement);
        return myself;
    }

}
