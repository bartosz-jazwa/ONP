package com.Kurs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CalcONP {
    private Deque<String> arguments  = new ArrayDeque<>();
    private Deque<String> operands = new ArrayDeque<>();
    private Stack<Integer> stack = new Stack<>();

    public Integer calc(Deque<String> args) {
        Integer operand1 = 0;
        Integer operand2 = 0;
        int result = 0;
        while (args.size() > 0) {
            String arg = args.pollLast();
            if (isOperand(arg)) {
                switch (arg) {
                    case "+": {
                        operand1 = stack.pop();
                        operand2 = stack.pop();
                        result = operand1 + operand2;
                        stack.push(result);
                        break;
                    }
                    case "-": {
                        operand1 = stack.pop();
                        operand2 = stack.pop();
                        result = operand2 - operand1;
                        stack.push(result);
                        break;
                    }
                    case "*": {
                        operand1 = stack.pop();
                        operand2 = stack.pop();
                        result = operand1 * operand2;
                        stack.push(result);
                        break;
                    }
                    case "/": {
                        operand1 = stack.pop();
                        operand2 = stack.pop();
                        result = operand2 / operand1;
                        stack.push(result);
                        break;
                    }
                    case "^": {
                        operand1 = stack.pop();
                        operand2 = stack.pop();
                        result = operand1 ^ operand2;
                        stack.push(result);
                        break;
                    }
                }

            } else if (isArgument(arg)) {
                stack.push(Integer.parseInt(arg));
            }
        }
        return stack.pop();
    }
    public Deque<String> formToOnp(Deque<String> formula){
        while (formula.size()>0){
            if (isArgument(formula.peek())) {
                arguments.push(formula.poll());
            }
            else if (isOperand(formula.peek())){
                switch (formula.peek()){
                    case "(":{
                        operands.push(formula.poll());
                        break;
                    }
                    case ")":{
                        String operToArg="";
                        formula.poll();
                        do {
                            operToArg= operands.poll();
                            if (!operToArg.equals("(")&&!operToArg.equals(")")){
                                arguments.push(operToArg);
                            }

                        }while (!operToArg.equals("("));
                        break;
                    }
                    case "-":
                    case "+":{
                        if (operands.size()>0){
                            String lastOper = operands.peek();
                            switch (lastOper){
                                case "*":
                                case "/":{
                                    arguments.push(operands.poll());
                                    operands.push(formula.poll());
                                    break;
                                }
                                default:{
                                    operands.push(formula.poll());
                                    break;
                                }
                            }
                        } else {
                            operands.push(formula.poll());
                        }
                        break;
                    }
                    case "*":
                    case "/":{
                        operands.push(formula.poll());
                        break;
                    }
                }
            }
        }
        while (operands.size()>0){
            arguments.push(operands.poll());
        }
        return arguments;
    }

    private boolean isArgument(String input){

        Pattern number = Pattern.compile("\\d+");
        Matcher n = number.matcher(input);
        return n.matches();
    }
    private boolean isOperand(String input){
        Pattern operator = Pattern.compile("\\p{Punct}");
        Matcher o = operator.matcher(input);
        return o.matches();
    }
}
