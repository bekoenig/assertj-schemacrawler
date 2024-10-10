package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.FactoryBasedNavigableListAssert;
import schemacrawler.schema.NamedObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ListableNamedObjectAssert<
        SELF extends FactoryBasedNavigableListAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT>,
        ACTUAL extends List<? extends ELEMENT>,
        ELEMENT extends NamedObject,
        ELEMENT_ASSERT extends AbstractAssert<ELEMENT_ASSERT, ELEMENT>>
        extends FactoryBasedNavigableListAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT> {

    public ListableNamedObjectAssert(ACTUAL elements, AssertFactory<ELEMENT, ELEMENT_ASSERT> assertFactory) {
        super(elements, FactoryBasedNavigableListAssert.class, assertFactory);
    }

    @SuppressWarnings("unchecked")
    public ListableNamedObjectAssert(Collection<? extends ELEMENT> elements, AssertFactory<ELEMENT, ELEMENT_ASSERT> assertFactory) {
        super((ACTUAL) new ArrayList<>(elements), FactoryBasedNavigableListAssert.class, assertFactory);
    }

    public ELEMENT_ASSERT get(String name) {
        ELEMENT element = null;

        if (Objects.nonNull(actual)) {
            element = actual.stream()
                    .filter(x -> name.equals(x.getName()))
                    .findFirst().orElse(null);
        }

        return toAssert(element, "element with name " + name);
    }

}
