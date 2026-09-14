package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.Test;
import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.DataTypeType;
import schemacrawler.schema.JavaSqlType;
import schemacrawler.schema.SearchableType;

import java.util.List;
import java.util.function.Predicate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ColumnDataTypeAssertTest {

    @Test
    void satisfiesBaseType() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        ColumnDataType baseType = mock(ColumnDataType.class);
        when(columnDataType.getBaseType()).thenReturn(baseType);
        when(baseType.getName()).thenReturn("BASE");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.satisfiesBaseType(actual ->
                org.assertj.core.api.Assertions.assertThat(actual.getName()).isEqualTo("BASE"));
    }

    @Test
    void matchesCreateParameters() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getCreateParameters()).thenReturn("length");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesCreateParameters(Predicate.isEqual("length"));
    }

    @Test
    void matchesDatabaseSpecificTypeName() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getDatabaseSpecificTypeName()).thenReturn("VARCHAR2");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesDatabaseSpecificTypeName(Predicate.isEqual("VARCHAR2"));
    }

    @Test
    void matchesEnumValues() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getEnumValues()).thenReturn(List.of("A", "B"));

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesEnumValues(values -> values.contains("A"));
    }

    @Test
    void satisfiesJavaSqlType() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getJavaSqlType()).thenReturn(JavaSqlType.UNKNOWN);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.satisfiesJavaSqlType(actual ->
                org.assertj.core.api.Assertions.assertThat(actual).isSameAs(JavaSqlType.UNKNOWN));
    }

    @Test
    void matchesLiteralPrefix() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getLiteralPrefix()).thenReturn("'");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesLiteralPrefix(Predicate.isEqual("'"));
    }

    @Test
    void matchesLiteralSuffix() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getLiteralSuffix()).thenReturn("'");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesLiteralSuffix(Predicate.isEqual("'"));
    }

    @Test
    void matchesLocalTypeName() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getLocalTypeName()).thenReturn("VARCHAR");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesLocalTypeName(Predicate.isEqual("VARCHAR"));
    }

    @Test
    void matchesMaximumScale() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getMaximumScale()).thenReturn(10);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesMaximumScale(Predicate.isEqual(10));
    }

    @Test
    void matchesMinimumScale() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getMinimumScale()).thenReturn(2);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesMinimumScale(Predicate.isEqual(2));
    }

    @Test
    void matchesNumPrecisionRadix() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getNumPrecisionRadix()).thenReturn(10);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesNumPrecisionRadix(Predicate.isEqual(10));
    }

    @Test
    void matchesPrecision() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getPrecision()).thenReturn(38L);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesPrecision(Predicate.isEqual(38L));
    }

    @Test
    void matchesSearchable() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getSearchable()).thenReturn(SearchableType.searchable);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesSearchable(Predicate.isEqual(SearchableType.searchable));
    }

    @Test
    void matchesType() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getType()).thenReturn(DataTypeType.system);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesType(Predicate.isEqual(DataTypeType.system));
    }

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

    @Test
    void matchesStandardTypeName() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.getStandardTypeName()).thenReturn("VARCHAR");

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.matchesStandardTypeName(Predicate.isEqual("VARCHAR"));
    }

    @Test
    void isAutoIncrementable() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.isAutoIncrementable()).thenReturn(true);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.isAutoIncrementable(true);
    }

    @Test
    void isCaseSensitive() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.isCaseSensitive()).thenReturn(true);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.isCaseSensitive(true);
    }

    @Test
    void isEnumerated() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.isEnumerated()).thenReturn(true);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.isEnumerated(true);
    }

    @Test
    void isFixedPrecisionScale() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.isFixedPrecisionScale()).thenReturn(true);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.isFixedPrecisionScale(true);
    }

    @Test
    void isNullable() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.isNullable()).thenReturn(true);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.isNullable(true);
    }

    @Test
    void isUnsigned() {
        // GIVEN
        ColumnDataType columnDataType = mock(ColumnDataType.class);
        when(columnDataType.isUnsigned()).thenReturn(true);

        // WHEN
        ColumnDataTypeAssert columnDataTypeAssert = SchemaCrawlerAssertions.assertThat(columnDataType);

        // THEN
        columnDataTypeAssert.isUnsigned(true);
    }

}
