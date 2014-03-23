package org.eslion.nlp.pipeline;

import com.google.common.base.Function;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import org.eslion.nlp.domain.Annotation;
import org.eslion.nlp.domain.AnnotationType;
import org.eslion.nlp.domain.Text;
import org.eslion.nlp.domain.Token;

import java.io.IOException;
import java.io.InputStream;

public class POSAnnotator extends AbstractAnnotator {
    private POSTaggerME tagger;

    public POSAnnotator() {
        try {
            InputStream modelIn = getClass().getResourceAsStream("/models/en-pos-maxent.bin");
            POSModel posModel = new POSModel(modelIn);
            tagger = new POSTaggerME(posModel);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot initialize annotator: " + e.getMessage(), e);
        }
    }

    @Override
    public Text apply(Text text) {
        for (Token[] sentence : text.getSentencesWithTokens()) {
            String[] tag = tagger.tag(transform(sentence));
            for (int i = 0; i < tag.length; i++) {
                String pos = tag[i];
                Token token = sentence[i];
                if (!token.isAlpha())
                    continue;
                text.addAnnotation(new Annotation(token.getOffset(), token.getLength(), AnnotationType.valueOf(pos)));
            }
        }
        return text;
    }

}
