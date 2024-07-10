package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractObjectAssert;
import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;

public class AbstractGrantAssert<
        SELF extends AbstractGrantAssert<SELF, D>,
        D extends DatabaseObject>
        extends AbstractObjectAssert<SELF, Grant<D>> {

    private final Class<D> databaseObjectType;

    public AbstractGrantAssert(Grant<D> actual, Class<?> selfType, Class<D> databaseObjectType) {
        super(actual, selfType);
        this.databaseObjectType = databaseObjectType;
    }

    public SELF hasGrantee(String expected) {
        extracting(Grant::getGrantee).isEqualTo(expected);
        return myself;
    }

    public SELF hasGrantor(String expected) {
        extracting(Grant::getGrantor).isEqualTo(expected);
        return myself;
    }

    public SELF isGrantable(boolean expected) {
        extracting(Grant::isGrantable).isEqualTo(expected);
        return myself;
    }

    public PrivilegeAssert<D> parent() {
        return extracting(Grant::getParent)
                .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.privilege(databaseObjectType));
    }

}
