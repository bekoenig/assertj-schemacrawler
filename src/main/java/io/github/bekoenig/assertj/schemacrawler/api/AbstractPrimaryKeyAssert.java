package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.TableConstraintType;

import java.util.function.Consumer;

public abstract class AbstractPrimaryKeyAssert<SELF extends AbstractPrimaryKeyAssert<SELF>>
        extends AbstractTableConstraintAssert<SELF, PrimaryKey> {

    protected AbstractPrimaryKeyAssert(PrimaryKey actual, Class<?> selfType) {
        super(actual, selfType);
    }

    @SafeVarargs
    public final SELF satisfiesType(Consumer<TableConstraintType>... requirements) {
        extracting(PrimaryKey::getType).satisfies(requirements);
        return myself;
    }

}
