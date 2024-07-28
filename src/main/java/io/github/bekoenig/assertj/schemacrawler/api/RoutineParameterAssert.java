package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Routine;
import schemacrawler.schema.RoutineParameter;

public class RoutineParameterAssert<
        ACTUAL extends RoutineParameter<PARENT>,
        PARENT extends Routine>
        extends AbstractRoutineParameterAssert<RoutineParameterAssert<ACTUAL, PARENT>, ACTUAL, PARENT> {

    public RoutineParameterAssert(ACTUAL actual) {
        super(actual, RoutineParameterAssert.class);
    }

}
