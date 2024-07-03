package io.github.bekoenig.assertj.schemacrawler.internal;

import schemacrawler.schema.NamedObject;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class NamedObjectUtils {

    private NamedObjectUtils() {
    }

    public static <T extends NamedObject> T findOne(List<T> namedObjects, String name) {
        assertThat(namedObjects).isNotNull();
        assertThat(name).isNotNull();

        List<T> columns = namedObjects.stream()
                .filter(y -> y.getName()
                        .equals(name))
                .collect(Collectors.toList());

        assertThat(columns).hasSizeBetween(0, 1);

        return columns.isEmpty() ? null : columns.get(0);
    }

}
