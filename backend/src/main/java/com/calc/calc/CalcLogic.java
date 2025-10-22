package com.calc.calc;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class CalcLogic {
    String operators = "+-/*.";
    public double evaluate(String expressionStr){
        expressionStr = expressionStr.replace('×', '*').replace('÷', '/');
        String postfixExpression = InfixToPostfix.convert(expressionStr);
        return PostfixEvaluator.evaluate(postfixExpression);
    }
    private static class LastNumberResult {
        double value;
        int index;
        LastNumberResult(double value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    private LastNumberResult getLastNumber(String expressionStr){
        expressionStr = expressionStr.replace('×', '*').replace('÷', '/');
        if (!expressionStr.isEmpty() && operators.contains(
                String.valueOf(expressionStr.charAt(expressionStr.length() - 1))
        )) {
            throw new IllegalArgumentException("Invalid expression");
        }
        int i = expressionStr.length() - 1;
        while (i >= 0 && (Character.isDigit(expressionStr.charAt(i)) || expressionStr.charAt(i) == '.')) {
            i--;
        }
        String lastNumberStr = expressionStr.substring(i + 1);
        double lastNumber = Double.parseDouble(lastNumberStr);
        return new LastNumberResult(lastNumber, i);
    }
    public String sqrt(String expressionStr){

        LastNumberResult lastNumber = getLastNumber(expressionStr);

        if (lastNumber.value < 0) {
            throw new ArithmeticException("Square root of negative number");
        }
        if(lastNumber.index >=0 && expressionStr.charAt(lastNumber.index) == '-'){
            throw new ArithmeticException("Square root of negative number");
        }
        double sqrtValue = Math.sqrt(lastNumber.value);
        DecimalFormat df = new DecimalFormat("#.#####");
        String formatted = df.format(sqrtValue);

        return expressionStr.substring(0, lastNumber.index + 1) + formatted;
    }
    public String pwrTwo(String expressionStr){
        LastNumberResult lastNumber = getLastNumber(expressionStr);
        int i = lastNumber.index;

        if (i > 0 && "+-×÷".indexOf(expressionStr.charAt(i-1)) != -1) {
            i--;
        }
        else if (i >=0 && expressionStr.charAt(i) == '-') {
            expressionStr = expressionStr.substring(0, i)+ '+' + expressionStr.substring(i + 1);
            if(expressionStr.charAt(0) == '+'){
                expressionStr = expressionStr.substring(1);
            }
        }
        double num = Math.pow(lastNumber.value, 2);
        DecimalFormat df = new DecimalFormat("#.#####");
        String formatted = df.format(num);

        return expressionStr.substring(0, i+1) + formatted;
    }

    public String toReciprocal(String expressionStr){

        LastNumberResult lastNumber = getLastNumber(expressionStr);
        if(lastNumber.value == 0) throw new ArithmeticException("Division by zero");
        double num = Math.pow(lastNumber.value, -1);
        DecimalFormat df = new DecimalFormat("#.#####");
        String formatted = df.format(num);

        return expressionStr.substring(0, lastNumber.index + 1) + formatted;
    }
    public String toPercent(String expressionStr){

        LastNumberResult lastNumber = getLastNumber(expressionStr);
        double num = lastNumber.value/100;
        DecimalFormat df = new DecimalFormat("#.#####");
        String formatted = df.format(num);

        return expressionStr.substring(0, lastNumber.index + 1) + formatted;
    }

    public String toNegate(String expressionStr){
        LastNumberResult lastNumber = getLastNumber(expressionStr);
        int i =  lastNumber.index;
        if(i==-1){
            return '-' + expressionStr;
        }
        if(expressionStr.charAt(i) == '-'){
            expressionStr =  expressionStr.substring(0, i) + expressionStr.substring(i + 1);
            return  expressionStr;
        }
        else{
            expressionStr = expressionStr.substring(0, i+1)+ '-' + expressionStr.substring(i + 1);
            return  expressionStr;
        }
    }
}
