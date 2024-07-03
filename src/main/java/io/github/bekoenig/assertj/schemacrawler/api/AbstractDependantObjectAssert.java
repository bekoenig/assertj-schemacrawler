package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.DependantObject;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractDependantObjectAssert<
        SELF extends AbstractDependantObjectAssert<SELF, ACTUAL, D>,
        ACTUAL extends DependantObject<D>,
        D extends DatabaseObject>
        extends AbstractDatabaseObjectAssert<SELF, ACTUAL> {

    protected AbstractDependantObjectAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesParent(Consumer<D> requirements) {
        extracting(DependantObject::getParent).satisfies(requirements);
        return myself;
    }

    public SELF matchesShortName(Predicate<String> predicate) {
        extracting(DependantObject::getShortName).matches(predicate);
        return myself;
    }

    public SELF isParentPartial(boolean expected) {
        return returns(expected, DependantObject::isParentPartial);
    }

}
