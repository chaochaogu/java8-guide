package com.chaochaogu.java8.method.reference;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public class Person {

    private Integer id;

    private String name;

    public Person(){

    }

    public Person(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
