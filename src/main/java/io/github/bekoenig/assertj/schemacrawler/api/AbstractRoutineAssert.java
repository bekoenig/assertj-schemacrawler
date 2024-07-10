package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.AbstractObjectAssert;
import schemacrawler.schema.*;

import java.util.List;

public class AbstractRoutineAssert<
        SELF extends AbstractRoutineAssert<SELF, ACTUAL>,
        ACTUAL extends Routine>
        extends AbstractDatabaseObjectAssert<SELF, ACTUAL> {

    protected AbstractRoutineAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public AbstractObjectAssert<?, List<RoutineParameter<? extends Routine>>> parameters() {
        // TODO: implement specific assert
        return extracting(Routine::getParameters);
    }

    public SELF hasReturnType(RoutineReturnType routineReturnType) {
        extracting(Routine::getReturnType).isEqualTo(routineReturnType);
        return myself;
    }

    public SELF hasRoutineBodyType(RoutineBodyType routineBodyType) {
        extracting(Routine::getRoutineBodyType).isEqualTo(routineBodyType);
        return myself;
    }

    public SELF hasRoutineType(RoutineType routineType) {
        extracting(Routine::getRoutineType).isEqualTo(routineType);
        return myself;
    }

    public SELF hasSpecificName(String specificName) {
        extracting(Routine::getSpecificName).isEqualTo(specificName);
        return myself;
    }

    public AbstractObjectAssert<?, ? extends RoutineParameter<? extends Routine>> parameter(String name) {
        return extracting(x -> x.lookupParameter(name)
                .orElse(null));
        // TODO: implement specific assert
        //.asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.routineParameter());
    }

}
