package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.DataTypeType;
import schemacrawler.schema.JavaSqlType;
import schemacrawler.schema.SearchableType;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractColumnDataTypeAssert<SELF extends AbstractColumnDataTypeAssert<SELF>>
        extends AbstractDatabaseObjectAssert<SELF, ColumnDataType> {

    protected AbstractColumnDataTypeAssert(ColumnDataType actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesBaseType(Consumer<ColumnDataType> requirement) {
        extracting(ColumnDataType::getBaseType)
                .satisfies(requirement);
        return myself;
    }

    public SELF matchesCreateParameters(Predicate<String> predicate) {
        extracting(ColumnDataType::getCreateParameters).matches(predicate);
        return myself;
    }

    public SELF matchesDatabaseSpecificTypeName(Predicate<String> predicate) {
        extracting(ColumnDataType::getDatabaseSpecificTypeName).matches(predicate);
        return myself;
    }

    public SELF matchesEnumValues(Predicate<List<String>> predicate) {
        extracting(ColumnDataType::getEnumValues).matches(predicate);
        return myself;
    }

    public SELF satisfiesJavaSqlType(Consumer<JavaSqlType> requirement) {
        extracting(ColumnDataType::getJavaSqlType).satisfies(requirement);
        return myself;
    }

    public SELF matchesLiteralPrefix(Predicate<String> predicate) {
        extracting(ColumnDataType::getLiteralPrefix).matches(predicate);
        return myself;
    }

    public SELF matchesLiteralSuffix(Predicate<String> predicate) {
        extracting(ColumnDataType::getLiteralSuffix).matches(predicate);
        return myself;
    }

    public SELF matchesLocalTypeName(Predicate<String> predicate) {
        extracting(ColumnDataType::getLocalTypeName).matches(predicate);
        return myself;
    }

    public SELF matchesMaximumScale(Predicate<Integer> predicate) {
        extracting(ColumnDataType::getMaximumScale).matches(predicate);
        return myself;
    }

    public SELF matchesMinimumScale(Predicate<Integer> predicate) {
        extracting(ColumnDataType::getMinimumScale).matches(predicate);
        return myself;
    }

    public SELF matchesNumPrecisionRadix(Predicate<Integer> predicate) {
        extracting(ColumnDataType::getNumPrecisionRadix).matches(predicate);
        return myself;
    }

    public SELF matchesPrecision(Predicate<Long> predicate) {
        extracting(ColumnDataType::getPrecision).matches(predicate);
        return myself;
    }

    public SELF matchesSearchable(Predicate<SearchableType> predicate) {
        extracting(ColumnDataType::getSearchable).matches(predicate);
        return myself;
    }

    public SELF matchesType(Predicate<DataTypeType> predicate) {
        extracting(ColumnDataType::getType).matches(predicate);
        return myself;
    }

    public SELF matchesTypeMappedClass(Predicate<Class<?>> predicate) {
        extracting(ColumnDataType::getTypeMappedClass).matches(predicate);
        return myself;
    }

    public SELF isAutoIncrementable(boolean expected) {
        return returns(expected, ColumnDataType::isAutoIncrementable);
    }

    public SELF isCaseSensitive(boolean expected) {
        return returns(expected, ColumnDataType::isCaseSensitive);
    }

    public SELF isEnumerated(boolean expected) {
        return returns(expected, ColumnDataType::isEnumerated);
    }

    public SELF isFixedPrecisionScale(boolean expected) {
        return returns(expected, ColumnDataType::isFixedPrecisionScale);
    }

    public SELF isNullable(boolean expected) {
        return returns(expected, ColumnDataType::isNullable);
    }

    public SELF isUnsigned(boolean expected) {
        return returns(expected, ColumnDataType::isUnsigned);
    }

}
