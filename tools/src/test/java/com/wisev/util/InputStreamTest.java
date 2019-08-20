package com.wisev.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class InputStreamTest {

    private static final Integer TEST = 1;

    public static void main(String[] args) throws Exception {
        Field field = InputStreamTest.class.getDeclaredField("TEST");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null,2);
        System.out.println(InputStreamTest.TEST);

    }
}
