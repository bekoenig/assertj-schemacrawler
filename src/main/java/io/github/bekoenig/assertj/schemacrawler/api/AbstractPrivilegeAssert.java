package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;
import schemacrawler.schema.Privilege;

import java.util.Collection;

public abstract class AbstractPrivilegeAssert<
        SELF extends AbstractPrivilegeAssert<SELF, ACTUAL, PARENT>,
        ACTUAL extends Privilege<PARENT>,
        PARENT extends DatabaseObject>
        extends AbstractDependantObjectAssert<SELF, ACTUAL, PARENT> {

    protected AbstractPrivilegeAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<? extends Grant<PARENT>>, Grant<PARENT>, GrantAssert<PARENT>> parameters() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getGrants(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

}
