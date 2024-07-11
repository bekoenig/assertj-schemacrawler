package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.ParameterModeType;
import schemacrawler.schema.RoutineParameter;

import java.util.function.Predicate;

public class AbstractRoutineParameterAssert<
        SELF extends AbstractRoutineParameterAssert<SELF, ACTUAL>,
        ACTUAL extends RoutineParameter<?>>
        extends AbstractBaseColumnAssert<SELF, ACTUAL> {

    protected AbstractRoutineParameterAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF hasParameterMode(ParameterModeType expected) {
        extracting(RoutineParameter::getParameterMode)
                .isEqualTo(expected);
        return myself;
    }

    public SELF matchesPrecision(Predicate<Integer> predicate) {
        extracting(RoutineParameter::getPrecision)
                .matches(predicate);
        return myself;
    }

}
