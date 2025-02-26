package io.github.bekoenig.assertj.schemacrawler.api;

import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;
import schemacrawler.schema.Schema;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchemaAssertTest {

    @Test
    void attribute() {
        // GIVEN
        Schema schema = mock();
        when(schema.lookupAttribute(any())).thenReturn(Optional.of(33));
        SchemaAssert schemaAssert = SchemaCrawlerAssertions.assertThat(schema);

        // WHEN
        ObjectAssert<?> optionalAssert = schemaAssert.attribute("attr1");

        // THEN
        optionalAssert.isNotNull().isEqualTo(33);
    }

    @Test
    void attribute_empty() {
        // GIVEN
        Schema schema = mock();
        when(schema.lookupAttribute(any())).thenReturn(Optional.empty());
        SchemaAssert schemaAssert = SchemaCrawlerAssertions.assertThat(schema);

        // WHEN
        ObjectAssert<?> optionalAssert = schemaAssert.attribute("attr1");

        // THEN
        optionalAssert.isNull();
    }
}