package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.util.Preconditions;
import schemacrawler.schema.NamedObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ListableNamedObjectAssert<
        SELF extends ListableNamedObjectAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT>,
        ACTUAL extends List<? extends ELEMENT>,
        ELEMENT extends NamedObject,
        ELEMENT_ASSERT extends AbstractAssert<ELEMENT_ASSERT, ELEMENT>>
        extends AbstractListAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT> {

    private final AssertFactory<ELEMENT, ELEMENT_ASSERT> assertFactory;

    public ListableNamedObjectAssert(ACTUAL elements, AssertFactory<ELEMENT, ELEMENT_ASSERT> assertFactory) {
        super(elements, ListableNamedObjectAssert.class);
        this.assertFactory = assertFactory;
    }

    @SuppressWarnings("unchecked")
    public ListableNamedObjectAssert(Collection<? extends ELEMENT> elements, AssertFactory<ELEMENT, ELEMENT_ASSERT> assertFactory) {
        super((ACTUAL) new ArrayList<>(elements), ListableNamedObjectAssert.class);
        this.assertFactory = assertFactory;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ELEMENT_ASSERT toAssert(ELEMENT value, String description) {
        return (ELEMENT_ASSERT) ((AbstractAssert)this.assertFactory.createAssert(value)).as(description, new Object[0]);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected SELF newAbstractIterableAssert(Iterable<? extends ELEMENT> iterable) {
        Preconditions.checkArgument(iterable instanceof List, "Expecting %s to be a List", iterable);
        return (SELF) new ListableNamedObjectAssert((List)iterable, this.assertFactory);
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
