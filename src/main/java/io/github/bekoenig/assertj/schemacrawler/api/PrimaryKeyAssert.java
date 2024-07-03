package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.PrimaryKey;

public class PrimaryKeyAssert extends AbstractPrimaryKeyAssert<PrimaryKeyAssert> {

    public PrimaryKeyAssert(PrimaryKey actual) {
        super(actual, PrimaryKeyAssert.class);
    }
}
