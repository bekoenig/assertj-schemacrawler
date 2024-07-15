package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractObjectAssert;
import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;

import java.util.function.Predicate;

public class AbstractGrantAssert<
        SELF extends AbstractGrantAssert<SELF>>
        extends AbstractObjectAssert<SELF, Grant<? extends DatabaseObject>> {

    public AbstractGrantAssert(Grant<? extends DatabaseObject> actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesGrantee(Predicate<String> predicate) {
        extracting(Grant::getGrantee).matches(predicate);
        return myself;
    }

    public SELF matchesGrantor(Predicate<String> predicate) {
        extracting(Grant::getGrantor).matches(predicate);
        return myself;
    }

    public SELF isGrantable(boolean expected) {
        extracting(Grant::isGrantable).isEqualTo(expected);
        return myself;
    }

}
