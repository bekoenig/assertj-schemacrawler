package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;

public class GrantAssert<PARENT extends DatabaseObject>
        extends AbstractGrantAssert<GrantAssert<PARENT>, Grant<PARENT>, PARENT> {

    public GrantAssert(Grant<PARENT> actual) {
        super(actual, GrantAssert.class);
    }

}
