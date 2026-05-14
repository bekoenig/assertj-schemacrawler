package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCompletenessTest extends AbstractApiTest {

    @TestFactory
    Stream<DynamicTest> testCompleteness() {
        return classMap.entrySet().stream()
                .map(entry -> DynamicTest.dynamicTest(entry.getKey().getSimpleName(),
                        () -> assertCompleteness(entry.getKey(), entry.getValue())));
    }

    private void assertCompleteness(Class<?> scClass, Class<?> assertClass) {
        Set<String> assertMethods = getAllMethods(assertClass);
        List<String> missingMethods = new ArrayList<>();

        for (Method method : scClass.getMethods()) {
            if (shouldBeCovered(method)) {
                if (!isCovered(scClass, method, assertMethods)) {
                    missingMethods.add(method.getName() + "(" + Arrays.toString(method.getParameterTypes()) + ")");
                }
            }
        }

        assertThat(missingMethods).as("Missing methods in %s for %s", assertClass.getSimpleName(), scClass.getSimpleName()).isEmpty();
    }

    private boolean isCovered(Class<?> scClass, Method scMethod, Set<String> assertMethods) {
        List<String> candidates = getCandidates(scClass, scMethod);
        for (String candidate : candidates) {
            if (assertMethods.contains(candidate)) {
                return true;
            }
        }
        return false;
    }
}
