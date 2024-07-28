package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractObjectAssert;
import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.DependantObject;

import java.util.function.Predicate;

public abstract class AbstractDependantObjectAssert<
        SELF extends AbstractDependantObjectAssert<SELF, ACTUAL, PARENT>,
        ACTUAL extends DependantObject<PARENT>,
        PARENT extends DatabaseObject>
        extends AbstractDatabaseObjectAssert<SELF, ACTUAL> {

    protected AbstractDependantObjectAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesShortName(Predicate<String> predicate) {
        extracting(DependantObject::getShortName).matches(predicate);
        return myself;
    }

    public SELF isParentPartial(boolean expected) {
        returns(expected, DependantObject::isParentPartial);
        return myself;
    }

    public AbstractObjectAssert<?, PARENT> parent() {
        return extracting(DependantObject::getParent);
    }

}
