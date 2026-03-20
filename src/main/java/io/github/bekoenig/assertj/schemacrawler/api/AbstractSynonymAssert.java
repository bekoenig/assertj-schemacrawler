package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractCollectionAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Synonym;

import java.util.Collection;
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

    public AbstractCollectionAssert<?, Collection<? extends DatabaseObject>, DatabaseObject, ObjectAssert<DatabaseObject>> referencedObjects() {
        return extracting(Synonym::getReferencedObjects, Assertions::assertThat);
    }

}
