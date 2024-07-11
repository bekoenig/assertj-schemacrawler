package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractObjectAssert;
import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;

import java.util.function.Predicate;

public class AbstractGrantAssert<
        SELF extends AbstractGrantAssert<SELF, D>,
        D extends DatabaseObject>
        extends AbstractObjectAssert<SELF, Grant<D>> {

    private final Class<D> databaseObjectType;

    public AbstractGrantAssert(Grant<D> actual, Class<?> selfType, Class<D> databaseObjectType) {
        super(actual, selfType);
        this.databaseObjectType = databaseObjectType;
    }

    public SELF matchesGrantee(Predicate<String> predicate) {
        extracting(Grant::getGrantee).matches(predicate);
        return myself;
    }

    public SELF matchesGrantor(Predicate<String> predicate) {
        extracting(Grant::getGrantor).matches(predicate);
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
