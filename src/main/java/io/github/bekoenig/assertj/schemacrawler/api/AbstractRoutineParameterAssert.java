package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.ParameterModeType;
import schemacrawler.schema.Routine;
import schemacrawler.schema.RoutineParameter;

import java.util.function.Predicate;

public class AbstractRoutineParameterAssert<
        SELF extends AbstractRoutineParameterAssert<SELF, ACTUAL, R>,
        ACTUAL extends RoutineParameter<R>,
        R extends Routine>
        extends AbstractBaseColumnAssert<SELF, ACTUAL, R> {

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
