package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractObjectAssert;
import schemacrawler.schema.NamedObject;
import schemacrawler.schema.NamedObjectKey;

import java.util.function.Predicate;

public abstract class AbstractNamedObjectAssert<
        SELF extends AbstractNamedObjectAssert<SELF, ACTUAL>, ACTUAL extends NamedObject>
        extends AbstractObjectAssert<SELF, ACTUAL> {

    protected AbstractNamedObjectAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesName(Predicate<String> predicate) {
        extracting(NamedObject::getName).matches(predicate);
        return myself;
    }

    public SELF hasName(String name) {
        return matchesName(Predicate.isEqual(name));
    }

    public SELF matchesFullName(Predicate<String> predicate) {
        extracting(NamedObject::getFullName).matches(predicate);
        return myself;
    }

    public SELF hasFullName(String fullName) {
        return matchesFullName(Predicate.isEqual(fullName));
    }

    public SELF matchesKey(Predicate<NamedObjectKey> predicate) {
        extracting(NamedObject::key).matches(predicate);
        return myself;
    }

}
