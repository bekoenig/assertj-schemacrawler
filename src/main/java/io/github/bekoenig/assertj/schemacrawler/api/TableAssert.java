package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Table;

public class TableAssert extends AbstractTableAssert<TableAssert, Table> {

    public TableAssert(Table actual) {
        super(actual, TableAssert.class);
    }

}
