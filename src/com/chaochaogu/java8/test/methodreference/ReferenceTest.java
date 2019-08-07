package com.chaochaogu.java8.test.methodreference;

import com.chaochaogu.java8.method.reference.Converter;
import com.chaochaogu.java8.method.reference.Person;
import com.chaochaogu.java8.method.reference.PersonFactory;
import com.chaochaogu.java8.method.reference.Something;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public class ReferenceTest {

    public static void main(String[] args) {
        Something something = new Something();
        Converter<String, Integer> converter = Integer::valueOf;
        Converter<String, String> converter1 = something::startsWith;
        String java = converter1.convert("JAVA");
        System.out.println(java);
        PersonFactory personFactory = Person::new;
        Person person = personFactory.create(1, "张三");



    }

}
