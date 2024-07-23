package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Synonym;

import java.util.function.Consumer;

public abstract class AbstractSynonymAssert<SELF extends AbstractSynonymAssert<SELF>>
        extends AbstractDatabaseObjectAssert<SELF, Synonym> {

    protected AbstractSynonymAssert(Synonym actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesReferencedObject(Consumer<DatabaseObject> requirement) {
        extracting(Synonym::getReferencedObject).satisfies(requirement);
        return myself;
    }

}
