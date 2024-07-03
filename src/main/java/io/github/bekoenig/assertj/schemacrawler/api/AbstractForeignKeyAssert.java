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

    @SafeVarargs
    public final SELF satisfiesDeferrability(Consumer<ForeignKeyDeferrability>... requirements) {
        extracting(ForeignKey::getDeferrability).satisfies(requirements);
        return myself;
    }

    public SELF matchesDefinition(Predicate<String> predicate) {
        extracting(ForeignKey::getDefinition).matches(predicate);
        return myself;
    }

    @SafeVarargs
    public final SELF satisfiesDeleteRule(Consumer<ForeignKeyUpdateRule>... requirements) {
        extracting(ForeignKey::getDeleteRule).satisfies(requirements);
        return myself;
    }

    @SafeVarargs
    public final SELF satisfiesType(Consumer<TableConstraintType>... requirements) {
        extracting(ForeignKey::getType).satisfies(requirements);
        return myself;
    }

    @SafeVarargs
    public final SELF satisfiesUpdateRule(Consumer<ForeignKeyUpdateRule>... requirements) {
        extracting(ForeignKey::getUpdateRule).satisfies(requirements);
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
