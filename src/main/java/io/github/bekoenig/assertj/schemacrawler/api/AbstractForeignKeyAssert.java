package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.ForeignKeyDeferrability;
import schemacrawler.schema.ForeignKeyUpdateRule;
import schemacrawler.schema.TableConstraintType;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractForeignKeyAssert<SELF extends AbstractForeignKeyAssert<SELF>>
        extends AbstractTableReferenceAssert<SELF, ForeignKey> {

    protected AbstractForeignKeyAssert(ForeignKey actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF satisfiesDeferrability(Consumer<ForeignKeyDeferrability> requirement) {
        extracting(ForeignKey::getDeferrability).satisfies(requirement);
        return myself;
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(ForeignKey::getDefinition).matches(predicate);
        return myself;
    }

    public SELF satisfiesDeleteRule(Consumer<ForeignKeyUpdateRule> requirement) {
        extracting(ForeignKey::getDeleteRule).satisfies(requirement);
        return myself;
    }

    public SELF satisfiesType(Consumer<TableConstraintType> requirement) {
        extracting(ForeignKey::getType).satisfies(requirement);
        return myself;
    }

    public SELF satisfiesUpdateRule(Consumer<ForeignKeyUpdateRule> requirement) {
        extracting(ForeignKey::getUpdateRule).satisfies(requirement);
        return myself;
    }

    public SELF hasDefinition(boolean expected) {
        return returns(expected, ForeignKey::hasDefinition);
    }

    public SELF isDeferrable(boolean expected) {
        return returns(expected, ForeignKey::isDeferrable);
    }

    public SELF isInitiallyDeferred(boolean expected) {
        return returns(expected, ForeignKey::isInitiallyDeferred);
    }

}
