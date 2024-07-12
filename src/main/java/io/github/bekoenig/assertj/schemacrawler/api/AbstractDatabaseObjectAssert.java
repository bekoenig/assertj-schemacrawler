package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Schema;

import java.util.function.Consumer;

public abstract class AbstractDatabaseObjectAssert<
        SELF extends AbstractDatabaseObjectAssert<SELF, ACTUAL>,
        ACTUAL extends DatabaseObject>
        extends AbstractNamedObjectWithAttributesAssert<SELF, ACTUAL> {

    protected AbstractDatabaseObjectAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public final SELF satisfiesSchema(Consumer<Schema> requirement) {
        extracting(DatabaseObject::getSchema).satisfies(requirement);
        return myself;
    }

}
