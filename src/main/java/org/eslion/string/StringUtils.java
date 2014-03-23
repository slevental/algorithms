package org.eslion.string;

import java.util.Stack;

/**
 * @author Stanislav Levental
 */
public class StringUtils {
    public static final char OPEN = '(';
    public static final char CLOSE = ')';

    public static int findUnbalancedBrackets(String str) {
        int balance = 0;
        int firstUnbalanced = -1;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case OPEN:
                    balance++;
                    if (firstUnbalanced < 0) {
                        firstUnbalanced = i;
                    }
                    break;
                case CLOSE:
                    if (--balance < 0) {
                        return i;
                    }
            }
            if (balance == 0) {
                firstUnbalanced = -1;
            }
        }
        return firstUnbalanced;
    }

    public static int findUnbalancedBracketsUsingStack(String str) {
        Stack<Integer> brackets = new Stack<Integer>();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case OPEN:
                    brackets.push(i);
                    continue;
                case CLOSE:
                    if (brackets.empty())
                        return i;
                    brackets.pop();
            }
        }
        return brackets.empty() ? -1 : brackets.firstElement();
    }

    public static String reverse(String str) {
        int len = str.length();
        if (len < 2)
            return str;
        int mid = len >>> 1;
        String p1 = str.substring(0, mid);
        String p2 = str.substring(mid + len % 2, len);
        return reverse(p2) + (len % 2 == 1 ? str.charAt(mid) : "") + reverse(p1);
    }
}
