package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.Test;
import schemacrawler.schema.Column;
import schemacrawler.schema.Grant;
import schemacrawler.schema.Privilege;

import java.util.function.Predicate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class GrantAssertTest {

    @Test
    void hasGrantee_Okay() {
        // GIVEN
        Grant<Column> actual = mock(Grant.class);
        when(actual.getGrantee()).thenReturn("someone");
        Class<Column> databaseTypeObject = Column.class;

        // WHEN
        GrantAssert<Column> grantAssert = SchemaCrawlerAssertions.assertThat(actual, databaseTypeObject);

        // THEN
        grantAssert.matchesGrantee(Predicate.isEqual("someone"));
    }

    @Test
    void hasGrantor_Okay() {
        // GIVEN
        Grant<Column> actual = mock(Grant.class);
        when(actual.getGrantor()).thenReturn("someone");
        Class<Column> databaseTypeObject = Column.class;

        // WHEN
        GrantAssert<Column> grantAssert = SchemaCrawlerAssertions.assertThat(actual, databaseTypeObject);

        // THEN
        grantAssert.matchesGrantor(Predicate.isEqual("someone"));
    }

    @Test
    void isGrantable_Okay() {
        // GIVEN
        Grant<Column> actual = mock(Grant.class);
        when(actual.isGrantable()).thenReturn(true);
        Class<Column> databaseTypeObject = Column.class;

        // WHEN
        GrantAssert<Column> grantAssert = SchemaCrawlerAssertions.assertThat(actual, databaseTypeObject);

        // THEN
        grantAssert.isGrantable(true);
    }

    @Test
    void parent() {
        // GIVEN
        Grant<Column> actual = mock(Grant.class);
        Privilege<Column> privilege = mock(Privilege.class);
        when(actual.getParent()).thenReturn(privilege);
        Class<Column> databaseTypeObject = Column.class;

        // WHEN
        GrantAssert<Column> grantAssert = SchemaCrawlerAssertions.assertThat(actual, databaseTypeObject);

        // THEN
        grantAssert.parent().isEqualTo(privilege);
    }

}