package com.calc.calc;

import java.util.Stack;

public class InfixToPostfix {

    static int prec(char c){
        if (c == '*' || c == '/' )
            return 2;
        else if (c == '+' || c == '-' )
            return 1;
        else
            return -1;
    }

    public static String convert(String expression){
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for(int i=0;i<expression.length();i++){
            char c = expression.charAt(i);
            if ((c == '-' || c == '+') &&
                    (i == 0 || "+-*/(".contains(String.valueOf(expression.charAt(i - 1))))) {

                result.append(c);
                i++;

                boolean dotSeen = false;
                while (i < expression.length()) {
                    char next = expression.charAt(i);
                    if (Character.isDigit(next)) {
                        result.append(next);
                    } else if (next == '.' && !dotSeen) {
                        result.append(next);
                        dotSeen = true;
                    } else {
                        break;
                    }
                    i++;
                }
                result.append(' ');
                i--;
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                boolean dotSeen = (c == '.');
                result.append(c);
                i++;

                while (i < expression.length()) {
                    char next = expression.charAt(i);
                    if (Character.isDigit(next)) {
                        result.append(next);
                    } else if (next == '.' && !dotSeen) {
                        result.append(next);
                        dotSeen = true;
                    } else {
                        break;
                    }
                    i++;
                }
                result.append(' ');
                i--;
            }
            else if(c == '('){
                stack.push(c);
            }
            else if(c == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    result.append(stack.pop()).append(' ');
                }
                if(!stack.isEmpty()) stack.pop();
            }
            else {
                while(!stack.isEmpty() && stack.peek() != '(' &&
                        prec(stack.peek()) >= prec(c)) {
                    result.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }

        while(!stack.isEmpty()){
            result.append(stack.pop()).append(' ');
        }

        return result.toString().trim();
    }
}
