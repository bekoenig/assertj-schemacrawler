package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
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

    public SELF matchesAttribute(String name, Predicate<? super Object> predicate) {
        extracting(x -> x.getAttribute(name))
                .matches(predicate);
        return myself;
    }

    public SELF matchesRemarks(Predicate<String> predicate) {
        extracting(DescribedObject::getRemarks)
                .matches(predicate);
        return myself;
    }

    public SELF hasAttribute(String name) {
        return returns(true, x -> x.getAttribute(name));
    }

    public SELF hasNoAttribute(String name) {
        return returns(false, x -> x.getAttribute(name));
    }

    public ObjectAssert<?> attribute(String name) {
        return extracting(x -> x.lookupAttribute(name).orElse(null), Assertions::assertThat);
    }

}
