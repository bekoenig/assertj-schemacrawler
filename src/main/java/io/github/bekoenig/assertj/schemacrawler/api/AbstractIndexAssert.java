package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.*;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractIndexAssert<SELF extends AbstractIndexAssert<SELF>>
        extends AbstractDependantObjectAssert<SELF, Index, Table> {

    protected AbstractIndexAssert(Index actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesCardinality(Predicate<Long> predicate) {
        extracting(Index::getCardinality).matches(predicate);
        return myself;
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<IndexColumn>, IndexColumn, IndexColumnAssert>>
    ListableNamedObjectAssert<MY_SELF, List<IndexColumn>, IndexColumn, IndexColumnAssert> columns() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getColumns(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(DefinedObject::getDefinition).matches(predicate);
        return myself;
    }

    public SELF hasIndexType(IndexType expected) {
        extracting(Index::getIndexType).isEqualTo(expected);
        return myself;
    }

    public SELF matchesPages(Predicate<Long> predicate) {
        extracting(Index::getPages).matches(predicate);
        return myself;
    }

    public SELF hasType(IndexType expected) {
        extracting(TypedObject::getType).isEqualTo(expected);
        return myself;
    }

    public SELF hasDefinition(boolean expected) {
        return returns(expected, DefinedObject::hasDefinition);
    }

    public SELF isUnique(boolean expected) {
        return returns(expected, Index::isUnique);
    }

    public IndexColumnAssert column(String name) {
        return extracting(x -> x.lookupColumn(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

}
