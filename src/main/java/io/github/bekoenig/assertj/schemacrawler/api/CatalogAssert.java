package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Catalog;

public class CatalogAssert extends AbstractCatalogAssert<CatalogAssert> {

    public CatalogAssert(Catalog actual) {
        super(actual, CatalogAssert.class);
    }

}
