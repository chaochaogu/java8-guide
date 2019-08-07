package com.chaochaogu.java8.myInterface;

/**
 * @author chaochao Gu
 * @date 2019/8/2
 */
public interface MyInterface {

    static int calculate(){
        return 1;
    }

    default void talk(){
        System.out.println("I am huaman!!");
    };

    void eat();

}
