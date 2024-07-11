package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Privilege;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractPrivilegeAssert<
        SELF extends AbstractPrivilegeAssert<SELF, ACTUAL, D>,
        ACTUAL extends Privilege<D>,
        D extends DatabaseObject>
        extends AbstractDependantObjectAssert<SELF, ACTUAL, D> {

    protected AbstractPrivilegeAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public GrantAssert<D> grants(Class<D> databaseObjectType) {
        return extracting(Privilege::getGrants)
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.grant(databaseObjectType));
    }

}
