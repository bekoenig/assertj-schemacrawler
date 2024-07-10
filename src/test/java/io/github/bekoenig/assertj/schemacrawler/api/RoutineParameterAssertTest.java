package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.Test;
import schemacrawler.schema.ParameterModeType;
import schemacrawler.schema.Routine;
import schemacrawler.schema.RoutineParameter;

import java.util.function.Predicate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class RoutineParameterAssertTest {

    @Test
    void hasParameterMode() {
        // GIVEN
        RoutineParameter<Routine> routineParameter = mock(RoutineParameter.class);
        when(routineParameter.getParameterMode()).thenReturn(ParameterModeType.unknown);

        // WHEN
        RoutineParameterAssert<Routine> routineParameterAssert = SchemaCrawlerAssertions.assertThat(routineParameter);

        // THEN
        routineParameterAssert.hasParameterMode(ParameterModeType.unknown);
    }

    @Test
    void hasPrecision() {
        // GIVEN
        RoutineParameter<Routine> routineParameter = mock(RoutineParameter.class);
        when(routineParameter.getPrecision()).thenReturn(7);

        // WHEN
        RoutineParameterAssert<Routine> routineParameterAssert = SchemaCrawlerAssertions.assertThat(routineParameter);

        // THEN
        routineParameterAssert.matchesPrecision(Predicate.isEqual(7));
    }

}