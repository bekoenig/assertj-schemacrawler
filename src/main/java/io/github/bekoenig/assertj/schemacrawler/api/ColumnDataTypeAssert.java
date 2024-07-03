package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.ColumnDataType;

public class ColumnDataTypeAssert extends AbstractColumnDataTypeAssert<ColumnDataTypeAssert> {

    protected ColumnDataTypeAssert(ColumnDataType actual) {
        super(actual, ColumnDataTypeAssert.class);
    }

}
