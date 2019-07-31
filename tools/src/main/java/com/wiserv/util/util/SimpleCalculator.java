package com.wiserv.util.util;

import java.math.BigDecimal;
import java.util.Stack;

public class SimpleCalculator {


    private static final char EOF_SYMBOL = '#';

    private static final String SYMBOL_LIST =  "+-*/%^!()#";

    public static final char[][] PRI = {//运算符优先等级
            //         |----------------当前运算符----------------|
            //            +   -   *   /   %   ^   !   (   )   #
            /*     + */ {'>','>','<','<','<','<','<','<','>','>'},
            /*     - */ {'>','>','<','<','<','<','<','<','>','>'},
            /* 栈  * */ {'>','>','>','>','>','<','<','<','>','>'},
            /* 顶  / */ {'>','>','>','>','>','<','<','<','>','>'},
            /* 运  % */ {'>','>','>','>','>','<','<','<','>','>'},
            /* 算  ^ */ {'>','>','>','>','>','>','<','<','>','>'},
            /* 符  ! */ {'>','>','>','>','>','>','>',' ','>','>'},
            /*     ( */ {'<','<','<','<','<','<','<','<','=',' '},
            /*     ) */ {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
            /*     # */ {'<','<','<','<','<','<','<','<',' ','='}
    };


    /**
     * 支持整数的四则运算
     * @param
     * @return
     */
    public static BigDecimal calculate(String expression) throws Exception {
        Stack<BigDecimal> numberStack = new Stack<>();
        Stack<Character> symbolStack = new Stack<>();
        //去掉空格
        expression = expression.replaceAll("\\s","") + EOF_SYMBOL;
        symbolStack.push(EOF_SYMBOL);
        int i = 0;
        while(!symbolStack.isEmpty()) {
            //如果是数字
            if(Character.isDigit(expression.charAt(i))) {
                i = readNumber(expression,i,numberStack);
            }else {
                // 当前扫描到的表达式符号
                char currentSymbol = expression.charAt(i);
                char priority = comparePriority(symbolStack.peek(),currentSymbol);
                switch (priority) {
                    case '>':
                        char symbol = symbolStack.pop();// 取出栈顶操作符
                        Calculator ca = new Calculator();// 基本计算操作对象
                        if ('!' == symbol) {// 一元运算符的计算
                            BigDecimal number = numberStack.pop();// 取出操作数栈顶数值
                            numberStack.push(ca.calculate(number, symbol));// 计算并将结果入栈
                        } else {// 二元运算符的计算
                            BigDecimal number2 = numberStack.pop();// 取出操作数栈顶数值
                            BigDecimal number1 = numberStack.pop();// 取出操作数栈顶数值
                            numberStack.push(ca.calculate(number1, symbol, number2));// 计算并将结果入栈
                        }
                        break;
                    case '<':// 栈顶运算符的优先级小于当前运算符，计算推迟，当前运算符入栈
                        symbolStack.push(currentSymbol);
                        i++;
                        break;
                    case '=':// 栈顶运算符的优先级等于当前运算符，脱括号并接收下一个字符
                        symbolStack.pop();
                        i++;
                        break;
                    case ' ':
                        throw new Exception("未知的操作符");

                }
            }

        }
        return numberStack.pop();
    }

    private static int readNumber(String expression,int index,Stack<BigDecimal> numberStack) {
        StringBuffer buffer = new StringBuffer();
        while(Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.') {
            buffer.append(expression.charAt(index));
            index ++;
        }
        numberStack.push(new BigDecimal(Double.valueOf(buffer.toString())));
        return index;
    }

    private static char comparePriority(char symbol1,char symbol2) {
        int i = SYMBOL_LIST.indexOf(symbol1);
        int j = SYMBOL_LIST.indexOf(symbol2);
        return PRI[i][j];
    }

    public static void main(String[] args) throws Exception {
        SimpleCalculator.calculate("(1 + 1) * 10!");
    }

}
