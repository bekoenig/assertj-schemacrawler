package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Schema;

import java.util.function.Predicate;

public abstract class AbstractSchemaAssert<
        SELF extends AbstractSchemaAssert<SELF, ACTUAL>,
        ACTUAL extends Schema>
        extends AbstractNamedObjectWithAttributesAssert<SELF, Schema> {

    protected AbstractSchemaAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesCatalogName(Predicate<String> predicate) {
        extracting(Schema::getCatalogName).matches(predicate);
        return myself;
    }

}
