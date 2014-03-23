package org.eslion.nlp.pipeline;

import org.eslion.nlp.domain.AnnotationType;
import org.eslion.nlp.domain.Text;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class POSAnnotatorTest {
    @Test
    public void testPOSAnnotator() throws Exception {
        TokenAnnotator token = new TokenAnnotator();
        POSAnnotator pos = new POSAnnotator();
        SentenceAnnotator sentence = new SentenceAnnotator();
        Text text = new Text("Stress Engineer Glasgow Salary **** to **** We re currently looking for talented engineers to join our growing Glasgow team at a variety of levels. The roles are ideally suited to high calibre engineering graduates with any level of appropriate experience, so that we can give you the opportunity to use your technical skills to provide high quality input to our aerospace projects, spanning both aerostructures and aeroengines. org.eslion.In return, you can expect good career opportunities and the chance for advancement and personal and professional development, support while you gain Chartership and some opportunities to possibly travel or work in other offices, in or outside of the UK. The Requirements You will need to have a good engineering degree that includes structural analysis (such as aeronautical, mechanical, automotive, civil) with some experience in a professional engineering environment relevant to (but not limited to) the aerospace sector. You will need to demonstrate experience in at least one or more of the following areas: Structural/stress analysis Composite stress analysis (any industry) Linear and nonlinear finite element analysis Fatigue and damage tolerance Structural dynamics Thermal analysis Aerostructures experience You will also be expected to demonstrate the following qualities: A strong desire to progress quickly to a position of leadership Professional approach Strong communication skills, written and verbal Commercial awareness Team working, being comfortable working in international teams and self managing PLEASE NOTE SECURITY CLEARANCE IS REQUIRED FOR THIS ROLE Stress Engineer Glasgow Salary **** to ****");
        String[] apply = pos.apply(sentence.apply(token.apply(text))).splitByType(
                AnnotationType.NNS
        ); //FW  NNP
        System.out.println(Arrays.toString(apply));
    }
}
