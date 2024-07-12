package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.FactoryBasedNavigableListAssert;
import schemacrawler.schema.*;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractIndexAssert<SELF extends AbstractIndexAssert<SELF>>
        extends AbstractDependantObjectAssert<SELF, Index> {

    protected AbstractIndexAssert(Index indexColumns, Class<?> selfType) {
        super(indexColumns, selfType);
    }

    public SELF matchesCardinality(Predicate<Long> predicate) {
        extracting(Index::getCardinality).matches(predicate);
        return myself;
    }

    public FactoryBasedNavigableListAssert<?, List<? extends IndexColumn>, IndexColumn, IndexColumnAssert> columns() {
        isNotNull();
        return new FactoryBasedNavigableListAssert<>(
                actual.getColumns(),
                FactoryBasedNavigableListAssert.class, SchemaCrawlerAssertions::assertThat);
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
        return extracting(x -> x.lookupColumn(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.indexColumn());
    }

}
