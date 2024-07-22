package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.TableConstraint;
import schemacrawler.schema.TableConstraintColumn;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractTableConstraintColumnAssert<
        SELF extends AbstractTableConstraintColumnAssert<SELF, ACTUAL>,
        ACTUAL extends TableConstraintColumn>
        extends AbstractKeyColumnAssert<SELF, ACTUAL> {

    protected AbstractTableConstraintColumnAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesTableConstraint(Consumer<TableConstraint> requirement) {
        extracting(TableConstraintColumn::getTableConstraint).satisfies(requirement);
        return myself;
    }

    public SELF matchesTableConstraintOrdinalPosition(Predicate<Integer> predicate) {
        extracting(TableConstraintColumn::getTableConstraintOrdinalPosition).matches(predicate);
        return myself;
    }

}
