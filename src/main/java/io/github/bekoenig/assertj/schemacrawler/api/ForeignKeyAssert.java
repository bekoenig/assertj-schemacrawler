package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.ForeignKey;

public class ForeignKeyAssert extends AbstractForeignKeyAssert<ForeignKeyAssert> {

    public ForeignKeyAssert(ForeignKey actual) {
        super(actual, ForeignKeyAssert.class);
    }

}
