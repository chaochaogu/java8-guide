package com.chaochaogu.java8.test.lamda;

import com.chaochaogu.java8.method.reference.Converter;
import org.omg.CORBA.INTERNAL;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public class LamdaTest {

    public static void main(String[] args) {
        final int num = 1;
        Converter<Integer, String> converter = (from) -> {
            return String.valueOf(from + num);
        };
        int num1 = 2;
        Converter<Integer, String> converter1 = (from) -> {
            return String.valueOf(from + num1);
        };




    }
}
