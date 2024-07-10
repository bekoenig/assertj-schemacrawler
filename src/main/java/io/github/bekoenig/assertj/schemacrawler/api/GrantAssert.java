package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;

public class GrantAssert<D extends DatabaseObject> extends AbstractGrantAssert<GrantAssert<D>, D> {

    public GrantAssert(Grant<D> actual, Class<D> databaseObjectType) {
        super(actual, GrantAssert.class, databaseObjectType);
    }

}
