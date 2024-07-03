package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Column;

public class ColumnAssert extends AbstractColumnAssert<ColumnAssert> {

    public ColumnAssert(Column actual) {
        super(actual, ColumnAssert.class);
    }

}
