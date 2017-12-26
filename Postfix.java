
/** *******************************************************************
 * Purpose/Description: <This program takes an infix notation and uses methods
 * inside the program to the postfix expression.>
 * Certification: I hereby certify that this work is my own and none of it is
 * the work of any other person.
 * ******************************************************************
 */
import java.util.Stack;

public class Postfix {

    /**
     * Uses other methods in the class in order to successfully convert the
     * infix expression into a postfix expression.
     *
     * @param stringValue the string you wish to change from infix to postfix
     * expression.
     * @return postfix string.
     */
    String infixToPostfix(String stringValue) {
        final int START = 0;
        char[] value = stringValue.toCharArray();
        Stack<Character> charStack = new Stack();
        String postfix = "";
        for (int iterate = START; iterate < value.length; iterate++) {
            if (isOperator(value[iterate])) {
                while (!charStack.isEmpty() && charStack.peek() != '('
                        && precedence(charStack.peek(), value[iterate])) {
                    postfix += charStack.peek();
                    charStack.pop();
                }
                charStack.push(value[iterate]);
            } else if (isOperand(value[iterate])) {
                postfix += value[iterate];
            } else if (value[iterate] == '(') {
                charStack.push(value[iterate]);
            } else if (value[iterate] == ')') {
                while (!charStack.isEmpty() && charStack.peek() != '(') {
                    postfix += charStack.peek();
                    charStack.pop();
                }
                charStack.pop();
            }
        }
        while (!charStack.isEmpty()) {
            postfix += charStack.peek();
            charStack.pop();
        }
        return postfix;
    }

    /**
     * Checks the operand char to make sure it is lowercase.
     *
     * @param test char at the moment you are testing.
     * @return true if it is a lower case letter, false if it is not.
     */
    boolean isOperand(char test) {
        if (test >= 'a' && test <= 'z') {
            return true;
        }
        return false;
    }

    /**
     * Checks the char to see if it is an operand.
     *
     * @param test char at the moment you are testing.
     * @return true if it is an operand, false if it is not.
     */
    boolean isOperator(char test) {
        if (test == '+' || test == '-' || test == '*' || test == '/') {
            return true;
        }
        return false;
    }

    /**
     * Tests the importance of the operator.
     *
     * @param test operator char.
     * @return the importance integer that weighs the importance of the
     * operator.
     */
    int getImportance(char test) {
        int importance = -1;
        if (test == '+' || test == '-') {
            importance = 1;
        } else if (test == '*' || test == '/') {
            importance = 2;
        } else {
            importance = 3;
        }
        return importance;
    }

    /**
     * Checks which of the two operators is more important using the importance
     * method.
     *
     * @param test1 first operator.
     * @param test2 second operator
     * @return true if the first operator is more important, false if it is not.
     */
    boolean precedence(char test1, char test2) {
        int test1Weight = getImportance(test1);
        int test2Weight = getImportance(test2);
        if (test1Weight > test2Weight) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String original = "(a*b-c)+d/e=";
        int count = 0;
        final int TO_ELIMINATE = 1;
        char[] charArray = new char[original.length() - TO_ELIMINATE];
        while (original.charAt(count) != '=') {
            charArray[count] = original.charAt(count);
            count++;
        }
        String finalTest = String.valueOf(charArray);
        Postfix postTest = new Postfix();
        String postfix = postTest.infixToPostfix(finalTest);
        System.out.println(postfix);
    }
}
