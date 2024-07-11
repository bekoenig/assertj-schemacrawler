package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.Test;
import schemacrawler.schema.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class RoutineAssertTest {

    @Test
    void parameters() {
        // GIVEN
        Routine actual = mock(Routine.class);
        RoutineParameter<Routine> parameter = mock(RoutineParameter.class);
        List<RoutineParameter<? extends Routine>> parameters = List.of(parameter);
        when(actual.getParameters()).thenReturn(parameters);

        // WHEN
        RoutineAssert routineAssert = SchemaCrawlerAssertions.assertThat(actual);

        // THEN
        routineAssert.parameters().isEqualTo(parameters).first().isEqualTo(parameter);

    }

    @Test
    void hasReturnType() {
        // GIVEN
        Routine actual = mock(Routine.class);
        when(actual.getReturnType()).thenReturn(FunctionReturnType.unknown);

        // WHEN
        RoutineAssert routineAssert = SchemaCrawlerAssertions.assertThat(actual);

        // THEN
        routineAssert.hasReturnType(FunctionReturnType.unknown);
    }

    @Test
    void hasRoutineBodyType() {
        // GIVEN
        Routine actual = mock(Routine.class);
        when(actual.getRoutineBodyType()).thenReturn(RoutineBodyType.unknown);

        // WHEN
        RoutineAssert routineAssert = SchemaCrawlerAssertions.assertThat(actual);

        // THEN
        routineAssert.hasRoutineBodyType(RoutineBodyType.unknown);
    }

    @Test
    void hasRoutineType() {
        // GIVEN
        Routine actual = mock(Routine.class);
        when(actual.getRoutineType()).thenReturn(RoutineType.unknown);

        // WHEN
        RoutineAssert routineAssert = SchemaCrawlerAssertions.assertThat(actual);

        // THEN
        routineAssert.hasRoutineType(RoutineType.unknown);
    }

    @Test
    void matchesSpecificName() {
        // GIVEN
        Routine actual = mock(Routine.class);
        when(actual.getSpecificName()).thenReturn("anything");

        // WHEN
        RoutineAssert routineAssert = SchemaCrawlerAssertions.assertThat(actual);

        // THEN
        routineAssert.matchesSpecificName(Predicate.isEqual("anything"));
    }

    @Test
    void parameter() {
        // GIVEN
        Routine actual = mock(Routine.class);
        RoutineParameter<Routine> parameter = mock(RoutineParameter.class);
        when(actual.lookupParameter(eq("any"))).thenReturn(Optional.of(parameter));

        // WHEN
        RoutineAssert routineAssert = SchemaCrawlerAssertions.assertThat(actual);

        // THEN
        routineAssert.parameter("any").isEqualTo(parameter);
    }
}