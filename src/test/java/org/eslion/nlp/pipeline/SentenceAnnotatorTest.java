package org.eslion.nlp.pipeline;

import org.eslion.nlp.domain.AnnotationType;
import org.eslion.nlp.domain.Text;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SentenceAnnotatorTest {

    @Test
    public void testSentenceAnnotator() throws Exception {
        SentenceAnnotator sa = new SentenceAnnotator();
        Text text = new Text("My name is test. I like fail, because I'm failed test. What is your name?");
        assertArrayEquals(
                new String[]{
                        "My name is test.",
                        "I like fail, because I'm failed test.",
                        "What is your name?"
                },
                sa.apply(text).getSentences()
        );
    }
}
