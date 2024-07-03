package io.github.bekoenig.assertj.schemacrawler.api;

import schemacrawler.schema.Sequence;

public class SequenceAssert extends AbstractSequenceAssert<SequenceAssert> {

    public SequenceAssert(Sequence actual) {
        super(actual, SequenceAssert.class);
    }

}
