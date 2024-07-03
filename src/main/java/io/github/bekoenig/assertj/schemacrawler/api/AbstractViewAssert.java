package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.ClassBasedNavigableIterableAssert;
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

    @SafeVarargs
    public final SELF satisfiesCheckOption(Consumer<CheckOptionType>... requirements) {
        extracting(View::getCheckOption).satisfies(requirements);
        return myself;
    }

    public ClassBasedNavigableIterableAssert<?, Collection<Table>, Table, TableAssert> tableUsage() {
        isNotNull();
        return new ClassBasedNavigableIterableAssert<>(actual.getTableUsage(), ClassBasedNavigableIterableAssert.class,
                TableAssert.class);
    }

    public SELF isUpdatable(boolean expected) {
        return returns(expected, View::isUpdatable);
    }

    public TableAssert table(String schemaName, String tableName) {
        return extracting(x -> x.lookupTable(x.getSchema(), tableName)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.table());
    }

}
