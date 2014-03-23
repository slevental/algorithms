package org.eslion.nlp.domain;

import com.google.common.collect.ComparisonChain;

public class Annotation implements Comparable<Annotation> {
    private final int offset;
    private final int length;
    private final AnnotationType type;

    public Annotation(int offset, int length, AnnotationType type) {
        this.offset = offset;
        this.length = length;
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public AnnotationType getType() {
        return type;
    }

    @Override
    public int compareTo(Annotation o) {
        return ComparisonChain.start()
                .compare(this.offset, o.offset)
                .compare(o.length, this.length)
                .compare(this.type, o.type)
                .result();
    }

    @Override
    public String toString() {
        return "Annotation{" +
                "offset=" + offset +
                ", length=" + length +
                ", type=" + type +
                '}';
    }
}
