package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.*;

import java.util.Set;
import java.util.function.Predicate;

public class AbstractTriggerAssert<SELF extends AbstractTriggerAssert<SELF>>
        extends AbstractDependantObjectAssert<SELF, Trigger, Table> {

    protected AbstractTriggerAssert(Trigger actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesActionCondition(Predicate<String> predicate) {
        extracting(Trigger::getActionCondition).matches(predicate);
        return myself;
    }

    public SELF matchesActionOrder(Predicate<Integer> predicate) {
        extracting(Trigger::getActionOrder).matches(predicate);
        return myself;
    }

    public SELF matchesActionOrientation(Predicate<ActionOrientationType> predicate) {
        extracting(Trigger::getActionOrientation).matches(predicate);
        return myself;
    }

    public SELF matchesActionStatement(Predicate<String> predicate) {
        extracting(Trigger::getActionStatement).matches(predicate);
        return myself;
    }

    public SELF matchesConditionTiming(Predicate<ConditionTimingType> predicate) {
        extracting(Trigger::getConditionTiming).matches(predicate);
        return myself;
    }

    public SELF matchesEventManipulationType(Predicate<Set<EventManipulationType>> predicate) {
        extracting(Trigger::getEventManipulationTypes).matches(predicate);
        return myself;
    }

}
