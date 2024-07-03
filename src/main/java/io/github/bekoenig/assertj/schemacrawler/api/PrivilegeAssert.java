package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Privilege;

public class PrivilegeAssert<D extends DatabaseObject>
        extends AbstractPrivilegeAssert<PrivilegeAssert<D>, Privilege<D>, D> {

    public PrivilegeAssert(Privilege<D> actual) {
        super(actual, Privilege.class);
    }

}
