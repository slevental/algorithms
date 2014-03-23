package org.eslion.nlp.domain;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class Text {
    private final String text;
    private final Set<Annotation> annotations = new TreeSet<Annotation>();


    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void addAnnotation(Annotation a) {
        annotations.add(a);
    }

    public List<Annotation> getAnnotations() {
        return ImmutableList.copyOf(annotations);
    }

    public String[] getSentences() {
        return splitByType(AnnotationType.SENTENCE);
    }

    public String[] getTokens() {
        return splitByType(AnnotationType.TOKEN);
    }

    public Iterable<Token[]> getSentencesWithTokens() {
        List<Token[]> res = new ArrayList<Token[]>();
        List<Token> sentence = null;
        for (Annotation each : annotations) {
            switch (each.getType()) {
                case SENTENCE:
                    if (sentence != null)
                        res.add(sentence.toArray(new Token[sentence.size()]));
                    sentence = new ArrayList<Token>();
                    break;
                case TOKEN:
                    if (sentence == null)
                        throw new IllegalStateException("Token without sentence: " + each);
                    sentence.add(new Token(each.getOffset(), each.getLength(),
                            text.substring(each.getOffset(), each.getOffset() + each.getLength())));
                    break;
                default:
            }
        }
        if (sentence != null)
            res.add(sentence.toArray(new Token[sentence.size()]));
        return res;
    }

    private Iterable<Annotation> getAnnotationsByType(final boolean inverse, final AnnotationType... type) {
        final EnumSet<AnnotationType> types = EnumSet.copyOf(Arrays.asList(type));
        return Collections2.filter(annotations, new Predicate<Annotation>() {
            @Override
            public boolean apply(Annotation annotation) {
                return inverse ^ types.contains(annotation.getType());
            }
        });
    }

    public String[] splitByType(AnnotationType... types) {
        return splitByType(false, types);
    }

    public String[] splitByType(boolean inverse, AnnotationType... type) {
        List<Annotation> annotations = ImmutableList.copyOf(getAnnotationsByType(inverse, type));
        String[] res = new String[annotations.size()];
        for (int i = 0; i < annotations.size(); i++) {
            Annotation a = annotations.get(i);
            res[i] = StringUtils.substring(text, a.getOffset(), a.getLength() + a.getOffset());
        }
        return res;
    }
}
