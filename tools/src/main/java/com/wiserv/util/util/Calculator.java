package com.wiserv.util.util;

import java.math.BigDecimal;

public class Calculator {


    public BigDecimal calculate(BigDecimal number,Character symbol) throws Exception {
        switch (symbol) {
            case '!' : return fact(number);
        }
        throw new Exception("未知的运算符");
    }

    public BigDecimal calculate(BigDecimal number1,Character symbol,BigDecimal number2) throws Exception {
        switch (symbol) {
            case '+' : return number1.add(number2);
            case '-' : return number1.subtract(number2);
            case '/' : return number1.divide(number2);
            case '*' : return number1.multiply(number2);
            case '%' : return mod(number1,number2);
            case '^' : return pow(number1,number2);
        }
        throw new Exception("未知的运算符");
    }

    /**
     *
     * @param number
     * @return 阶乘运算
     * @throws Exception
     */
    private BigDecimal fact(BigDecimal number) throws Exception {
        if(new BigDecimal(number.intValue()).compareTo(number) != 0) {
            throw new Exception("阶乘运算数必须为整数！");
        }
        if (number.intValue() < 0) {
            throw new Exception("阶乘运算数不能为负数！");
        }
        if (number.intValue() == 0)
            return new BigDecimal(1);
        int num = number.intValue();
        BigDecimal result = new BigDecimal(1);
        while (num > 0) {
            result = result.multiply(new BigDecimal(num--)) ;
        }
        return result;
    }

    /**
     *
     * @param
     * @return 取模运算
     * @throws Exception
     */
    private BigDecimal mod(BigDecimal number1,BigDecimal number2) throws Exception {
        if(new BigDecimal(number1.intValue()).compareTo(number1) != 0 || new BigDecimal(number2.intValue()).compareTo(number2) != 0 ) {
            throw new Exception("阶乘运算数必须为整数！");
        }
        if (number1.intValue() < 0 || number2.intValue() < 0) {
            throw new Exception("阶乘运算数不能为负数！");
        }
        int i = number1.intValue() % number2.intValue();
        return new BigDecimal(i);
    }

    /**
     *
     * @param
     * @return 冥运算
     * @throws Exception
     */
    private BigDecimal pow(BigDecimal number1, BigDecimal number2) throws Exception {
        double i = Math.pow(number1.doubleValue(),number2.doubleValue());
        return new BigDecimal(i);
    }





}
