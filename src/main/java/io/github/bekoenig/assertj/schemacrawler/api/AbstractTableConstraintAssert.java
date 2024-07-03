package io.github.bekoenig.assertj.schemacrawler.api;

import io.github.bekoenig.assertj.schemacrawler.internal.NamedObjectUtils;
import org.assertj.core.api.ListAssert;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableConstraint;
import schemacrawler.schema.TableConstraintColumn;
import schemacrawler.schema.TableConstraintType;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractTableConstraintAssert<
        SELF extends AbstractTableConstraintAssert<SELF, ACTUAL>,
        ACTUAL extends TableConstraint>
        extends AbstractDependantObjectAssert<SELF, ACTUAL, Table> {

    protected AbstractTableConstraintAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public ListAssert<TableConstraintColumn> constrainedColumns() {
        return assertThat(actual.getConstrainedColumns());
    }

    public TableConstraintColumnAssert constrainedColumn(String columnName) {
        return extracting(x -> NamedObjectUtils.findOne(x.getConstrainedColumns(), columnName))
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.tableConstraintColumn());
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(TableConstraint::getDefinition).matches(predicate);
        return myself;
    }

    public SELF hasType(TableConstraintType tableConstraintType) {
        extracting(TableConstraint::getType).isEqualTo(tableConstraintType);
        return myself;
    }

    public SELF hasDefinition(boolean expected) {
        return returns(expected, TableConstraint::hasDefinition);
    }

    public SELF isDeferrable(boolean expected) {
        return returns(expected, TableConstraint::isDeferrable);
    }

    public SELF isInitiallyDeferred(boolean expected) {
        return returns(expected, TableConstraint::isInitiallyDeferred);
    }

}
