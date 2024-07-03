package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractCollectionAssert;
import org.assertj.core.api.ObjectAssert;
import schemacrawler.schema.DatabaseObject;
import schemacrawler.schema.Grant;
import schemacrawler.schema.Privilege;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractPrivilegeAssert<
        SELF extends AbstractPrivilegeAssert<SELF, ACTUAL, D>,
        ACTUAL extends Privilege<D>,
        D extends DatabaseObject>
        extends AbstractDependantObjectAssert<SELF, ACTUAL, D> {

    protected AbstractPrivilegeAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    // TODO: implement specific grant assert
    public AbstractCollectionAssert<?, Collection<? extends Grant<D>>, Grant<D>, ObjectAssert<Grant<D>>> grants() {
        isNotNull();
        return assertThat(actual.getGrants());
    }

}
