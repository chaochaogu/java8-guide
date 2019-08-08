package com.chaochaogu.java8.method.reference;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public interface PersonFactory<P extends Person> {

    P create(String name, Integer id);
}
