package org.eslion.wordnet;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static junit.framework.Assert.assertEquals;

public class WordNetTest {
    public static final Random rnd = new Random();

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void testSAP_closer() throws Exception {
        WordNet wn = new WordNet(prepare(
                        "1,animals,animals\n" +
                        "2,chordate,chordate\n" +
                        "3,mammalia,mammalia\n" +
                        "4,carnivora,carnivora\n" +
                        "5,ferret,ferret"),
                prepare("2,1\n3,2\n4,3\n5,4\n5,1"));
        assertEquals("animals", wn.sap("chordate", "ferret"));
    }

    @Test
    public void testSAP_root() throws Exception {
        WordNet wn = new WordNet(prepare(
                        "1,animals,animals\n" +
                        "2,chordate,chordate\n" +
                        "3,mammalia,mammalia\n" +
                        "4,carnivora,carnivora\n" +
                        "5,ferret,ferret"),
                prepare("1,2\n2,3\n4,3\n5,3"));
        assertEquals("mammalia", wn.sap("animals", "ferret"));
        assertEquals(3, wn.distance("animals", "ferret"));
    }

    private String prepare(String data) throws IOException {
        File synsets = tmp.newFile(String.valueOf(rnd.nextInt() & 0x7fffffff));
        FileUtils.writeStringToFile(synsets, data);
        return synsets.getAbsolutePath();
    }
}
