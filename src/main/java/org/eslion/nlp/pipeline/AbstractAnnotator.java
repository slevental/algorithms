package org.eslion.nlp.pipeline;

import com.google.common.base.Function;
import org.eslion.nlp.domain.Text;
import org.eslion.nlp.domain.Token;

abstract class AbstractAnnotator implements Function<Text, Text> {
    protected String[] transform(Token[] tokens) {
        String[] res = new String[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            res[i] = tokens[i].getText();
        }
        return res;
    }
}
