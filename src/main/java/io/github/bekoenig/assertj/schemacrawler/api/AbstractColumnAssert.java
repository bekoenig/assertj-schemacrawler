package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.Privilege;
import schemacrawler.schema.Table;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractColumnAssert<SELF extends AbstractColumnAssert<SELF>>
        extends AbstractBaseColumnAssert<SELF, Column> {

    protected AbstractColumnAssert(Column actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesDefaultValue(Predicate<String> predicate) {
        extracting(Column::getDefaultValue).matches(predicate);
        return myself;
    }

    public FactoryBasedNavigableIterableAssert<?, Iterable<? extends Privilege<?>>, Privilege<?>, PrivilegeAssert> privileges() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getPrivileges(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

    @SafeVarargs
    public final SELF satisfiesReferencedColumn(Consumer<Column>... requirements) {
        extracting(Column::getReferencedColumn).satisfies(requirements);
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
