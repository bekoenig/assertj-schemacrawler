package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Routine;

// TODO: Implement specific abstract assertions
public class RoutineAssert extends AbstractDatabaseObjectAssert<RoutineAssert, Routine> {

    public RoutineAssert(Routine actual) {
        super(actual, RoutineAssert.class);
    }

}
