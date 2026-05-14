package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DeprecationConsistencyTest extends AbstractApiTest {

    @TestFactory
    Stream<DynamicTest> testDeprecationConsistency() {
        return classMap.entrySet().stream()
                .map(entry -> DynamicTest.dynamicTest(entry.getKey().getSimpleName(),
                        () -> assertDeprecationConsistency(entry.getKey(), entry.getValue())));
    }

    private void assertDeprecationConsistency(Class<?> scClass, Class<?> assertClass) {
        Map<String, Boolean> assertMethodsDeprecation = getAllMethodsWithDeprecation(assertClass);
        List<String> inconsistencies = new ArrayList<>();

        for (Method scMethod : scClass.getMethods()) {
            if (scMethod.isAnnotationPresent(Deprecated.class)) {
                List<String> candidates = getCandidates(scClass, scMethod);
                boolean consistencyFound = false;
                for (String candidate : candidates) {
                    if (assertMethodsDeprecation.containsKey(candidate) && assertMethodsDeprecation.get(candidate)) {
                        consistencyFound = true;
                        break;
                    }
                }
                if (!consistencyFound) {
                    inconsistencies.add(String.format("SchemaCrawler method %s.%s is deprecated, but no corresponding deprecated assertion was found in %s (candidates: %s)",
                            scClass.getSimpleName(), scMethod.getName(), assertClass.getSimpleName(), candidates));
                }
            }
        }

        assertThat(inconsistencies).as("Deprecation inconsistencies for %s", scClass.getSimpleName()).isEmpty();
    }
}
