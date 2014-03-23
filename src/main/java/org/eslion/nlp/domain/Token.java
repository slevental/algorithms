package org.eslion.nlp.domain;

import org.apache.commons.lang.StringUtils;

public class Token {
    private final int offset;
    private final int length;
    private final String text;

    public Token(int offset, int length, String text) {
        this.offset = offset;
        this.length = length;
        this.text = text;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public String getText() {
        return text;
    }

    public boolean isAlpha(){
        return StringUtils.isAlpha(text);
    }
}
