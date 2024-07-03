package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Schema;

public class SchemaAssert extends AbstractSchemaAssert<SchemaAssert, Schema> {

    public SchemaAssert(Schema actual) {
        super(actual, SchemaAssert.class);
    }

}
