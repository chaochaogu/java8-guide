package com.chaochaogu.java8.method.reference;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
@FunctionalInterface
public interface Converter<T, K> {

    K convert(T t);
}
