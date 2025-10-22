package com.calc.calc;

import java.util.Stack;

public class PostfixEvaluator {
    public static double evaluate(String postfixExpression){
        String[] tokens = postfixExpression.split(" ");
        Stack<Double> stack = new Stack<>();
        for (String token : tokens) {
            if(token.matches("-?\\d+(\\.\\d+)?")){
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch(token){
                    case "+": stack.push(a+b); break;
                    case "-": stack.push(a-b); break;
                    case "*": stack.push(a*b); break;
                    case "/":
                        if(b == 0) throw new ArithmeticException("Division by zero");
                        stack.push(a/b);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
