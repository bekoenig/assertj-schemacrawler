package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Privilege;

public class PrivilegeAssert<PARENT extends DatabaseObject>
        extends AbstractPrivilegeAssert<PrivilegeAssert<PARENT>, Privilege<PARENT>, PARENT> {

    public PrivilegeAssert(Privilege<PARENT> actual) {
        super(actual, PrivilegeAssert.class);
    }

}
