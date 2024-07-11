package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Grant;

public class GrantAssert extends AbstractGrantAssert<GrantAssert> {

    public GrantAssert(Grant<?> actual) {
        super(actual, GrantAssert.class);
    }

}
