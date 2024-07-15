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
        ELEMENT extends NamedObject,
        ELEMENT_ASSERT extends AbstractAssert<ELEMENT_ASSERT, ELEMENT>>
        extends FactoryBasedNavigableListAssert<
        ListableNamedObjectAssert<ELEMENT, ELEMENT_ASSERT>,
        List<ELEMENT>, ELEMENT, ELEMENT_ASSERT> {

    @SuppressWarnings("unchecked")
    public ListableNamedObjectAssert(List<? extends ELEMENT> elements, AssertFactory<ELEMENT, ELEMENT_ASSERT> assertFactory) {
        super((List<ELEMENT>) elements, ListableNamedObjectAssert.class, assertFactory);
    }

    public ListableNamedObjectAssert(Collection<? extends ELEMENT> elements, AssertFactory<ELEMENT, ELEMENT_ASSERT> assertFactory) {
        super(new ArrayList<>(elements), ListableNamedObjectAssert.class, assertFactory);
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
