package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Privilege;

public abstract class AbstractPrivilegeAssert<
        SELF extends AbstractPrivilegeAssert<SELF, ACTUAL>,
        ACTUAL extends Privilege<?>>
        extends AbstractDependantObjectAssert<SELF, ACTUAL> {

    protected AbstractPrivilegeAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public GrantAssert grants() {
        return extracting(Privilege::getGrants)
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.grant());
    }

}
