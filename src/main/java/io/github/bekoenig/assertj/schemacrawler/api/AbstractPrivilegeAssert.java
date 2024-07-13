package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
import schemacrawler.schema.Grant;
import schemacrawler.schema.Privilege;

import java.util.Collection;

public abstract class AbstractPrivilegeAssert<
        SELF extends AbstractPrivilegeAssert<SELF, ACTUAL>,
        ACTUAL extends Privilege<?>>
        extends AbstractDependantObjectAssert<SELF, ACTUAL> {

    protected AbstractPrivilegeAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public FactoryBasedNavigableIterableAssert<?, Collection<? extends Grant<?>>, Grant<?>, GrantAssert> parameters() {
        isNotNull();
        return new FactoryBasedNavigableIterableAssert<>(
                actual.getGrants(),
                FactoryBasedNavigableIterableAssert.class, SchemaCrawlerAssertions::assertThat);
    }

}
