package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;

public class GrantAssert extends AbstractGrantAssert<GrantAssert, Grant<? extends DatabaseObject>> {

    public GrantAssert(Grant<? extends DatabaseObject> actual) {
        super(actual, GrantAssert.class);
    }

}
