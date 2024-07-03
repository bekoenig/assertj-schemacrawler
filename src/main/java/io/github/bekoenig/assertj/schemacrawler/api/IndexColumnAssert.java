package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.IndexColumn;

public class IndexColumnAssert extends AbstractIndexColumnAssert<IndexColumnAssert> {

    public IndexColumnAssert(IndexColumn actual) {
        super(actual, IndexColumnAssert.class);
    }

}
