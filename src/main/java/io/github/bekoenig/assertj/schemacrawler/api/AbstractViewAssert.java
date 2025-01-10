package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
import schemacrawler.schema.CheckOptionType;
import schemacrawler.schema.Table;
import schemacrawler.schema.View;

import java.util.Collection;
import java.util.function.Consumer;

public abstract class AbstractViewAssert<SELF extends AbstractViewAssert<SELF>>
        extends AbstractTableAssert<SELF, View> {

    protected AbstractViewAssert(View actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesCheckOption(Consumer<CheckOptionType> requirement) {
        extracting(View::getCheckOption).satisfies(requirement);
        return myself;
    }

    public <MY_SELF extends FactoryBasedNavigableIterableAssert<MY_SELF, Collection<Table>, Table, TableAssert>>
    FactoryBasedNavigableIterableAssert<MY_SELF, Collection<Table>, Table, TableAssert> tableUsage() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getTableUsage(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public SELF isUpdatable(boolean expected) {
        return returns(expected, View::isUpdatable);
    }

    public TableAssert table(String schemaName, String tableName) {
        return extracting(x -> x.lookupTable(x.getSchema(), tableName).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.table());
    }

}
