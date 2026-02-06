package project20280.stacksqueues;

import project20280.interfaces.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        ArrayStack<Character> nextClosingBracket = new ArrayStack<>();
        for (char c : input.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                nextClosingBracket.push(c == '(' ? ')' : c == '{' ? '}' : ']');
            }
            else if (c == ')' || c == '}' || c == ']') {
                if (nextClosingBracket.isEmpty()) {
                    System.out.println("Invalid");
                    return;
                }
                if (c != nextClosingBracket.pop()) {
                    System.out.println("Invalid");
                    return;
                }
            }

        }

        if (!nextClosingBracket.isEmpty()) {
            System.out.println("Invalid");
        }
        else {
            System.out.println("Valid");
        }

    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}