package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Sequence;

import java.math.BigInteger;
import java.util.function.Predicate;

public abstract class AbstractSequenceAssert<SELF extends AbstractSequenceAssert<SELF>>
        extends AbstractDatabaseObjectAssert<SELF, Sequence> {

    protected AbstractSequenceAssert(Sequence actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF matchesIncrement(Predicate<Long> predicate) {
        extracting(Sequence::getIncrement).matches(predicate);
        return myself;
    }

    public SELF matchesMaximumValue(Predicate<BigInteger> predicate) {
        extracting(Sequence::getMaximumValue).matches(predicate);
        return myself;
    }

    public SELF matchesMinimumValue(Predicate<BigInteger> predicate) {
        extracting(Sequence::getMinimumValue).matches(predicate);
        return myself;
    }

    public SELF matchesStartValue(Predicate<BigInteger> predicate) {
        extracting(Sequence::getStartValue).matches(predicate);
        return myself;
    }

    public SELF isCycle(boolean expected) {
        return returns(expected, Sequence::isCycle);
    }

}
