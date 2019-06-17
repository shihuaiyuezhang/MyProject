package com.wiserv.util.word;

public class AlgorithmQuiz {

    public static void main(String[] args)  {
        int[] ints = new int[]{0,2,3,1,5};

        int result = 0;
        for(int i = 0; i <= 5; i++) {
            result ^= i;
            if(i < ints.length) {
                result ^= ints[i];
            }
        }
        System.out.println(result);
    }


}
