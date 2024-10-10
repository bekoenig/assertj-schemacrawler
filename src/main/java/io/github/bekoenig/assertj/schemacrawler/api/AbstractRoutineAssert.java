package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.*;

import java.util.List;
import java.util.function.Predicate;

public class AbstractRoutineAssert<
        SELF extends AbstractRoutineAssert<SELF, ACTUAL>,
        ACTUAL extends Routine>
        extends AbstractDatabaseObjectAssert<SELF, ACTUAL> {

    protected AbstractRoutineAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public <C extends RoutineParameter<P>, P extends Routine> ListableNamedObjectAssert<?, List<C>, C, RoutineParameterAssert<C, P>> parameters() {
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

    public RoutineParameterAssert<?, ?> parameter(String name) {
        return extracting(x -> x.lookupParameter(name).orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.routineParameter());
    }

}
