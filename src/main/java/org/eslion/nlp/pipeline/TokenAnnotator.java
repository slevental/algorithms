package org.eslion.nlp.pipeline;

import com.google.common.base.Function;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.eslion.nlp.domain.Annotation;
import org.eslion.nlp.domain.AnnotationType;
import org.eslion.nlp.domain.Text;

import java.io.IOException;
import java.io.InputStream;

public class TokenAnnotator implements Function<Text, Text> {
    private TokenizerME tokenizer;

    public TokenAnnotator() {
        try {
            InputStream modelIn = getClass().getResourceAsStream("/models/en-token.bin");
            TokenizerModel posModel = new TokenizerModel(modelIn);
            tokenizer = new TokenizerME(posModel);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot initialize annotator: " + e.getMessage(), e);
        }
    }

    @Override
    public Text apply(Text text) {
        for (Span each : tokenizer.tokenizePos(text.getText())) {
            text.addAnnotation(new Annotation(each.getStart(), each.getEnd() - each.getStart(), AnnotationType.TOKEN));
        }
        return text;
    }
}
