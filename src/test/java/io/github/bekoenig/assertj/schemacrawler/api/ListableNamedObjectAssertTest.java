package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.Test;
import schemacrawler.schema.Index;
import schemacrawler.schema.Table;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListableNamedObjectAssertTest {

    @Test
    void get() {
        // GIVEN
        Table table = mock();
        Index index = mock();
        when(index.getName()).thenReturn("some_index");
        when(table.getIndexes()).thenReturn(List.of(index));

        // WHEN // THEN
        SchemaCrawlerAssertions.assertThat(table)
                .indexes().get("some_index").isSameAs(index);
    }

    @Test
    void cast_toFactoryBasedNavigableListAssert() {
        // GIVEN
        Table table = mock();
        when(table.getIndexes()).thenReturn(List.of());

        // WHEN // THEN
        SchemaCrawlerAssertions.assertThat(table)
                .indexes()
                .filteredOn(idx -> !idx.isUnique()).isEmpty();
    }

}