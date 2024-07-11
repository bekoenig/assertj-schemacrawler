package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.RoutineParameter;

public class RoutineParameterAssert extends AbstractRoutineParameterAssert<RoutineParameterAssert, RoutineParameter<?>> {

    public RoutineParameterAssert(RoutineParameter<?> actual) {
        super(actual, RoutineParameterAssert.class);
    }

}
