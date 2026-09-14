package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.Test;
import schemacrawler.schema.ColumnDataType;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ColumnDataTypeAssertTest {

    @Test
    void matchesTypeMappedClassName() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getTypeMappedClassName()).thenReturn("java.lang.String");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesTypeMappedClassName(name -> name.equals("java.lang.String"));
    }

}
