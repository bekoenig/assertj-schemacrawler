package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Routine;
import schemacrawler.schema.RoutineParameter;

public class RoutineParameterAssert<R extends Routine> extends AbstractRoutineParameterAssert<RoutineParameterAssert<R>, RoutineParameter<R>, R> {

    public RoutineParameterAssert(RoutineParameter<R> actual) {
        super(actual, RoutineParameterAssert.class);
    }

}
