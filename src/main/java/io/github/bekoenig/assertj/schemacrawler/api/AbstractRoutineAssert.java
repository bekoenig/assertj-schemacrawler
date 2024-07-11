package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
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


    public <R extends Routine> FactoryBasedNavigableIterableAssert<?, List<RoutineParameter<R>>, RoutineParameter<R>, RoutineParameterAssert<R>> parameters() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getParameters(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    public SELF hasReturnType(RoutineReturnType routineReturnType) {
        extracting(Routine::getReturnType).isEqualTo(routineReturnType);
        return myself;
    }

    public SELF hasRoutineBodyType(RoutineBodyType routineBodyType) {
        extracting(Routine::getRoutineBodyType).isEqualTo(routineBodyType);
        return myself;
    }

    public SELF hasRoutineType(RoutineType routineType) {
        extracting(Routine::getRoutineType).isEqualTo(routineType);
        return myself;
    }

    public SELF matchesSpecificName(Predicate<String> predicate) {
        extracting(Routine::getSpecificName).matches(predicate);
        return myself;
    }

    public RoutineParameterAssert<?> parameter(String name) {
        return extracting(x -> x.lookupParameter(name)
                .orElse(null))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.routineParameter());
    }

}
