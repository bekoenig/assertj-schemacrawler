package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractKeyColumnAssert<
        SELF extends AbstractKeyColumnAssert<SELF, ACTUAL>,
        ACTUAL extends Column>
        extends AbstractDependantObjectAssert<SELF, ACTUAL, Table> {

    protected AbstractKeyColumnAssert(ACTUAL actual, Class<?> selfType) {
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

    public SELF matchesDefaultValue(Predicate<String> predicate) {
        extracting(Column::getDefaultValue).matches(predicate);
        return myself;
    }

    public SELF matchesOrdinalPosition(Predicate<Integer> predicate) {
        extracting(BaseColumn::getOrdinalPosition).matches(predicate);
        return myself;
    }

    public <MY_SELF extends ListableNamedObjectAssert<MY_SELF, List<Privilege<Column>>, Privilege<Column>, PrivilegeAssert<Column>>>
    ListableNamedObjectAssert<MY_SELF, List<Privilege<Column>>, Privilege<Column>, PrivilegeAssert<Column>> privileges() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getPrivileges(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF satisfiesReferencedColumn(Consumer<Column> requirement) {
        extracting(Column::getReferencedColumn).satisfies(requirement);
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

    public SELF isAutoIncremented(boolean expected) {
        return returns(expected, Column::isAutoIncremented);
    }

    public SELF isColumnDataTypeKnown(boolean expected) {
        return returns(expected, BaseColumn::getColumnDataType);
    }

    public SELF isGenerated(boolean expected) {
        return returns(expected, Column::isGenerated);
    }

    public SELF isHidden(boolean expected) {
        return returns(expected, Column::isHidden);
    }

    public SELF isNullable(boolean expected) {
        return returns(expected, BaseColumn::isNullable);
    }

    public SELF isPartOfForeignKey(boolean expected) {
        return returns(expected, Column::isPartOfForeignKey);
    }

    public SELF isPartOfIndex(boolean expected) {
        return returns(expected, Column::isPartOfIndex);
    }

    public SELF isPartOfPrimaryKey(boolean expected) {
        return returns(expected, Column::isPartOfPrimaryKey);
    }

    public SELF isPartOfUniqueIndex(boolean expected) {
        return returns(expected, Column::isPartOfUniqueIndex);
    }

    @SuppressWarnings("unchecked")
    public PrivilegeAssert<Column> privilege(String name) {
        return (PrivilegeAssert<Column>) extracting(x -> x.lookupPrivilege(name))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.privilege());
    }

}
