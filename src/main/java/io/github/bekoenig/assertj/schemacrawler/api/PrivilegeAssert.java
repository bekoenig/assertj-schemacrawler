package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Privilege;

public class PrivilegeAssert
        extends AbstractPrivilegeAssert<PrivilegeAssert, Privilege<? extends DatabaseObject>> {

    public PrivilegeAssert(Privilege<? extends DatabaseObject> actual) {
        super(actual, PrivilegeAssert.class);
    }

}
