package com.chaochaogu.java8.test.lamda;

import com.chaochaogu.java8.method.reference.Converter;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public class LamdaOuter {

    static int outerStaticNum;

    int num;

    public void testScope(){
        Converter<Integer, String> converter = (from) -> {
            outerStaticNum = 11;
            return String.valueOf(outerStaticNum + from);
        };
        Converter<Integer, String> converter1 = (from -> {
            num = 22;
            return String.valueOf(num + from);
        });

    }
}
