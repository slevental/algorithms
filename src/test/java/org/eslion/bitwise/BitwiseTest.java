package org.eslion.bitwise;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

import java.util.Random;

import static junit.framework.Assert.assertEquals;

public class BitwiseTest {

    @Test
    public void testNextPowerOf2() throws Exception {
        assertEquals(8, Bitwise.nextPowerOf2(7));
        assertEquals(8, Bitwise.nextPowerOf2(8));
        assertEquals(16, Bitwise.nextPowerOf2(9));
        assertEquals(0, Bitwise.nextPowerOf2(0));
    }
}
