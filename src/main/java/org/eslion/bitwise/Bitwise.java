package org.eslion.bitwise;

/**
 * Contains useful operations which using bitwise
 * operators
 */
public final class Bitwise {
    private Bitwise() {
    }

    public static boolean odd(int x) {
        return 0 == (x & (x - 1));
    }

    public static int nextPowerOf2(int x) {
        x = x - 1;
        x = x | (x >> 1);
        x = x | (x >> 2);
        x = x | (x >> 4);
        x = x | (x >> 8);
        x = x | (x >> 16);
        return x + 1;
    }
}
