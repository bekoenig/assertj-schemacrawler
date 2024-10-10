package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractColumnAssert<SELF extends AbstractColumnAssert<SELF>>
        extends AbstractBaseColumnAssert<SELF, Column, Table> {

    protected AbstractColumnAssert(Column actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesDefaultValue(Predicate<String> predicate) {
        extracting(Column::getDefaultValue).matches(predicate);
        return myself;
    }

    public ListableNamedObjectAssert<?, List<Privilege<Column>>, Privilege<Column>, PrivilegeAssert<Column>> privileges() {
        isNotNull();
        return new ListableNamedObjectAssert<>(
                actual.getPrivileges(),
                SchemaCrawlerAssertions::assertThat);
    }

    public SELF satisfiesReferencedColumn(Consumer<Column> requirement) {
        extracting(Column::getReferencedColumn).satisfies(requirement);
        return myself;
    }

    public SELF matchesColumnDataTypeName(Predicate<String> predicate) {
        extracting(Column::getColumnDataType)
                .extracting(ColumnDataType::getName)
                .matches(predicate);
        return myself;
    }

    public SELF isAutoIncremented(boolean expected) {
        return returns(expected, Column::isAutoIncremented);
    }

    public SELF isGenerated(boolean expected) {
        return returns(expected, Column::isGenerated);
    }

    public SELF isHidden(boolean expected) {
        return returns(expected, Column::isHidden);
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
}
