package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.BaseColumn;
import schemacrawler.schema.ColumnDataType;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractBaseColumnAssert<
        SELF extends AbstractBaseColumnAssert<SELF, ACTUAL>,
        ACTUAL extends BaseColumn<?>>
        extends AbstractDependantObjectAssert<SELF, ACTUAL> {

    protected AbstractBaseColumnAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesColumnDataType(Consumer<ColumnDataType> requirement) {
        extracting(BaseColumn::getColumnDataType).satisfies(requirement);
        return myself;
    }

    public SELF matchesDecimalDigits(Predicate<Integer> predicate) {
        extracting(BaseColumn::getDecimalDigits).matches(predicate);
        return myself;
    }

    public SELF matchesOrdinalPosition(Predicate<Integer> predicate) {
        extracting(BaseColumn::getOrdinalPosition).matches(predicate);
        return myself;
    }

    public SELF matchesSize(Predicate<Integer> predicate) {
        extracting(BaseColumn::getSize).matches(predicate);
        return myself;
    }

    public SELF satisfiesType(Consumer<ColumnDataType> requirement) {
        extracting(BaseColumn::getType).satisfies(requirement);
        return myself;
    }

    public SELF matchesWidth(Predicate<String> predicate) {
        extracting(BaseColumn::getWidth).matches(predicate);
        return myself;
    }

    public SELF isColumnDataTypeKnown(boolean expected) {
        return returns(expected, BaseColumn::getColumnDataType);
    }

    public SELF isNullable(boolean expected) {
        return returns(expected, BaseColumn::isNullable);
    }

}
