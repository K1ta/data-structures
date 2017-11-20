package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 * взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 * к которой приписана слева или справа правильная скобочная последовательность
 * — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        int k1 = 0, k2 = 0, k3 = 0;
        for (char c : sequence.toCharArray()) {
            switch (c) {
            case LEFT_BRACE:
                k1++;
                break;
            case LEFT_BRACKET:
                k2++;
                break;
            case LEFT_PAREN:
                k3++;
                break;
            case RIGHT_BRACE:
                k1--;
                break;
            case RIGHT_BRACKET:
                k2--;
                break;
            case RIGHT_PAREN:
                k3--;
                break;
            }
            if (k1 < 0 || k2 < 0 || k3 < 0) {
                return false;
            }
        }
        return k1 == 0 && k2 == 0 && k3 == 0;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
