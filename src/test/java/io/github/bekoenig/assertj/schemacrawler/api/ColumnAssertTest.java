package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.Table;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ColumnAssertTest {

    @Test
    void matchesDefaultValue_Okay() {
        // GIVEN
        Column column = mock(Column.class);
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(column.getColumnDataType()).thenReturn(columnDataType);
        when(columnDataType.getName()).thenReturn("MY_TYPE");

        // WHEN
        ColumnAssert columnAssert = SchemaCrawlerAssertions.assertThat(column);

        // THEN
        columnAssert.matchesColumnDataTypeName(x -> x.startsWith("MY_"));
    }

    @Test
    void matchesDefaultValue_Fehler1() {
        // GIVEN
        Column column = null;

        // WHEN
        ColumnAssert columnAssert = SchemaCrawlerAssertions.assertThat(column);

        // THEN
        Assertions.assertThatThrownBy(() -> columnAssert.matchesColumnDataTypeName("MY_"::startsWith))
                .isInstanceOf(AssertionError.class)
                .message()
                .contains("Expecting actual not to be null");
    }

    @Test
    void matchesDefaultValue_Fehler2() {
        // GIVEN
        Column column = mock(Column.class);
        when(column.getColumnDataType()).thenReturn(null);

        // WHEN
        ColumnAssert columnAssert = SchemaCrawlerAssertions.assertThat(column);

        // THEN
        Assertions.assertThatThrownBy(() -> columnAssert.matchesColumnDataTypeName("MY_"::startsWith))
                .isInstanceOf(AssertionError.class)
                .message()
                .contains("Expecting actual not to be null");
    }

    @Test
    void parent() {
        // GIVEN
        Column column = mock(Column.class);
        Table table = mock(Table.class);
        when(column.getParent()).thenReturn(table);
        when(column.getParent()).thenReturn(table);

        // WHEN
        ColumnAssert columnAssert = SchemaCrawlerAssertions.assertThat(column);

        // THEN
        columnAssert.parent();
    }

}