package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DependantObject;

import java.util.function.Predicate;

public abstract class AbstractDependantObjectAssert<
        SELF extends AbstractDependantObjectAssert<SELF, ACTUAL>,
        ACTUAL extends DependantObject<?>>
        extends AbstractDatabaseObjectAssert<SELF, ACTUAL> {

    protected AbstractDependantObjectAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesShortName(Predicate<String> predicate) {
        extracting(DependantObject::getShortName).matches(predicate);
        return myself;
    }

}
