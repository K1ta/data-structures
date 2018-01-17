package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 * <p>
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 * <p>
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char TIMES = '*';
    private static final char DIVISION = '/';

    private static double evaluate(String[] values) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i]);
        }
        return calculate(list);
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double calculate(ArrayList<String> list) {
        int state = 0;
        int index = 0;
        int newState = 0;
        while (list.size() > 1) {
            for (int i = 0; i < list.size(); i++) {
                state = (state == 3) ? 3 : newState;
                newState = getState(list.get(i));
                if (newState == 3) {
                    index = i;
                }
                if (state == 2 && newState == 0) {
                    double a = Double.parseDouble(list.remove(i - 2));
                    String s = list.remove(i - 2);
                    double b = Double.parseDouble(list.remove(i - 2));
                    list.add(i - 2, Double.toString(op(a, s, b)));
                    i = i - 2;
                } else if (state == 3 && newState == 4) {
                    ArrayList<String> tempList = new ArrayList<>();
                    for (int j = index; j <= i; j++) {
                        tempList.add(list.remove(index));
                    }
                    tempList.remove(0);
                    tempList.remove(tempList.size() - 1);
                    list.add(index, Double.toString(calculate(tempList)));
                    i = index;
                    state = 0;
                    newState = 0;
                } else if (state == 1 && newState == 0) {
                    if (i + 1 < list.size() && getState(list.get(i + 1)) == 1) {
                        double a = Double.parseDouble(list.remove(i - 2));
                        String s = list.remove(i - 2);
                        double b = Double.parseDouble(list.remove(i - 2));
                        list.add(i - 2, Double.toString(op(a, s, b)));
                        i = i - 2;
                    } else if (i + 1 >= list.size()) {
                        double a = Double.parseDouble(list.remove(i - 2));
                        String s = list.remove(i - 2);
                        double b = Double.parseDouble(list.remove(i - 2));
                        list.add(i - 2, Double.toString(op(a, s, b)));
                        i = i - 2;
                    }
                }
            }
        }
        return Double.parseDouble(list.get(0));
    }

    public static int getState(String s) {
        switch (s.charAt(0)) {
            case PLUS:
                return 1;
            case MINUS:
                return 1;
            case TIMES:
                return 2;
            case DIVISION:
                return 2;
            case LEFT_PAREN:
                return 3;
            case RIGHT_PAREN:
                return 4;
            default:
                return 0;
        }
    }

    public static double op(double a, String s, double b) {
        switch (s.charAt(0)) {
            case PLUS:
                return a + b;
            case MINUS:
                return a - b;
            case TIMES:
                return a * b;
            case DIVISION:
                return a / b;
            default:
                return 0;
        }
    }
}
