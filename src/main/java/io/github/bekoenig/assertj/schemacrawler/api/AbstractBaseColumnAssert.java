package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.BaseColumn;
import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.DatabaseObject;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractBaseColumnAssert<
        SELF extends AbstractBaseColumnAssert<SELF, ACTUAL, P>,
        ACTUAL extends BaseColumn<P>,
        P extends DatabaseObject>
        extends AbstractDependantObjectAssert<SELF, ACTUAL, P> {

    protected AbstractBaseColumnAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    @SafeVarargs
    public final SELF satisfiesColumnDataType(Consumer<ColumnDataType>... requirements) {
        extracting(BaseColumn::getColumnDataType).satisfies(requirements);
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

    @SafeVarargs
    public final SELF satisfiesType(Consumer<ColumnDataType>... requirements) {
        extracting(BaseColumn::getType).satisfies(requirements);
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
