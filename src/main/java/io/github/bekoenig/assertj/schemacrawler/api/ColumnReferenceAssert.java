package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.ColumnReference;

public class ColumnReferenceAssert extends AbstractColumnReferenceAssert<ColumnReferenceAssert> {

    public ColumnReferenceAssert(ColumnReference actual) {
        super(actual, ColumnReferenceAssert.class);
    }

}
