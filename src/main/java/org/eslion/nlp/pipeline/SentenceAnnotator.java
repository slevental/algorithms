package org.eslion.nlp.pipeline;


import com.google.common.base.Function;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;
import org.eslion.nlp.domain.Annotation;
import org.eslion.nlp.domain.AnnotationType;
import org.eslion.nlp.domain.Text;

import java.io.IOException;
import java.io.InputStream;

public class SentenceAnnotator implements Function<Text, Text> {
    private final SentenceDetectorME detector;

    public SentenceAnnotator() {
        try {
            InputStream modelIn = getClass().getResourceAsStream("/models/en-sent.bin");
            SentenceModel model = new SentenceModel(modelIn);
            detector = new SentenceDetectorME(model);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot create annotator due to error: " + e.getMessage(), e);
        }
    }

    @Override
    public Text apply(Text text) {
        for (Span sentence : detector.sentPosDetect(text.getText())) {
            text.addAnnotation(new Annotation(sentence.getStart(), sentence.getEnd() - sentence.getStart(), AnnotationType.SENTENCE));
        }
        return text;
    }
}
