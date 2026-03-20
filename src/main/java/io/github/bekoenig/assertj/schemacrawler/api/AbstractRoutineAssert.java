package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractCollectionAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import schemacrawler.schema.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AbstractRoutineAssert<
        SELF extends AbstractRoutineAssert<SELF, ACTUAL>,
        ACTUAL extends Routine>
        extends AbstractDatabaseObjectAssert<SELF, ACTUAL> {

    protected AbstractRoutineAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<C>, C, RoutineParameterAssert<C, P>>, C extends RoutineParameter<P>, P extends Routine>
    ListableNamedObjectAssert<MY_SELF, List<C>, C, RoutineParameterAssert<C, P>> parameters() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getParameters(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF hasReturnType(RoutineReturnType expected) {
        extracting(Routine::getReturnType).isEqualTo(expected);
        return myself;
    }

    public SELF hasRoutineBodyType(RoutineBodyType expected) {
        extracting(Routine::getRoutineBodyType).isEqualTo(expected);
        return myself;
    }

    public SELF hasRoutineType(RoutineType expected) {
        extracting(Routine::getRoutineType).isEqualTo(expected);
        return myself;
    }

    public SELF matchesSpecificName(Predicate<String> predicate) {
        extracting(Routine::getSpecificName).matches(predicate);
        return myself;
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(Routine::getDefinition).matches(predicate);
        return myself;
    }

    public SELF hasDefinition(boolean expected) {
        return returns(expected, Routine::hasDefinition);
    }

    public SELF satisfiesType(Consumer<Comparable<?>> requirement) {
        extracting(Routine::getType).satisfies(requirement);
        return myself;
    }

    public AbstractCollectionAssert<?, Collection<? extends DatabaseObject>, DatabaseObject, ObjectAssert<DatabaseObject>> referencedObjects() {
        return extracting(Routine::getReferencedObjects, Assertions::assertThat);
    }

    public RoutineParameterAssert<?, ?> parameter(String name) {
        return extracting(x -> x.lookupParameter(name).orElse(null),
                SchemaCrawlerAssertions::assertThat);
    }

}
