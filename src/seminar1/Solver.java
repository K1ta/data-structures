package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import seminar1.collections.LinkedQueue;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) ) ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 * <p>
 * Считаем, что операции деления на ноль отсутствуют
 */
public class Solver {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char TIMES = '*';
    private static final char DIVISION = '/';

    private static Node root = null;
    private static LinkedQueue<String> queue;

    private static double evaluate(String[] values) {
        queue = new LinkedQueue<>();
        for (int i = 0; i < values.length; i++) {
            queue.enqueue(values[i]);
        }
        root = put(root);
        return calculate(root);
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

    private static class Node {
        String value;
        Node left;
        Node right;

        Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static double calculate(Node node) {
        switch (node.value.charAt(0)) {
        case PLUS:
            return calculate(node.left) + calculate(node.right);
        case MINUS:
            return calculate(node.left) - calculate(node.right);
        case TIMES:
            return calculate(node.left) * calculate(node.right);
        case DIVISION:
            return calculate(node.left) / calculate(node.right);
        default:
            return Double.parseDouble(node.value);
        }
    }

    private static Node put(Node cur) {
        if (queue.isEmpty()) {
            return cur;
        }
        String value = queue.dequeue();
        while (value.charAt(0) == RIGHT_PAREN) {
            value = queue.dequeue();
        }
        if (value.charAt(0) == LEFT_PAREN) {
            cur = new Node(null, null, null);
            cur.left = put(cur.left);
            value = queue.dequeue();
            while (value.charAt(0) == RIGHT_PAREN) {
                value = queue.dequeue();
            }
            cur.value = value;
            cur.right = put(cur.right);
        } else {
            cur = new Node(value, null, null);
            return cur;
        }
        return cur;
    }
}
