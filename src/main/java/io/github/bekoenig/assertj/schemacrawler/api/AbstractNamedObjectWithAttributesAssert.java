package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.OptionalAssert;
import schemacrawler.schema.AttributedObject;
import schemacrawler.schema.DescribedObject;
import schemacrawler.schema.NamedObject;

import java.util.function.Predicate;

public abstract class AbstractNamedObjectWithAttributesAssert<
        SELF extends AbstractNamedObjectWithAttributesAssert<SELF, ACTUAL>,
        ACTUAL extends NamedObject & AttributedObject & DescribedObject>
        extends AbstractNamedObjectAssert<SELF, ACTUAL> {

    protected AbstractNamedObjectWithAttributesAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public <T> SELF matchesAttribute(String name, Class<T> type, Predicate<T> predicate) {
        extracting(x -> x.getAttribute(name))
                .asInstanceOf(InstanceOfAssertFactories.type(type))
                .matches(predicate);
        return myself;
    }

    public SELF matchesRemarks(Predicate<String> predicate) {
        extracting(DescribedObject::getRemarks).matches(predicate);
        return myself;
    }

    public SELF hasAttribute(String name) {
        return returns(true, x -> x.getAttribute(name));
    }

    public SELF hasNoAttribute(String name) {
        return returns(false, x -> x.getAttribute(name));
    }

    public <T> OptionalAssert<T> attribute(String name, Class<T> resultType) {
        return extracting(x -> x.lookupAttribute(name))
                .asInstanceOf(InstanceOfAssertFactories.optional(resultType));
    }

}
