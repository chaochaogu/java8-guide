package com.chaochaogu.java8.test.streams;

import java.util.*;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public class Streams {

    public static void main(String[] args) {

        List<String> myList = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2");
        myList.stream()
                .filter((s)->s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
        boolean anyMatch = myList.stream().anyMatch((s) -> s.startsWith("a"));
        long count = myList.stream().filter((s) -> s.startsWith("b")).count();
        Optional<String> reduce = myList.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        // Parallel Stream 支持并发，效率高
        long count1 = myList.parallelStream().sorted().count();
        Map<Integer, String> map = new HashMap<>(16);
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, value)-> System.out.println(value));
        map.computeIfPresent(3, (num, value) -> value + num);
        map.get(3);
        map.computeIfPresent(9, (num, value) -> null);
        map.containsKey(9);
        map.computeIfAbsent(23, (num) -> "val" + num);
        map.containsKey(23);
        map.computeIfAbsent(3, (num) -> "bam");
        map.get(3);
        map.remove(3, "val3");
        map.get(3);
        map.remove(3, "val33");
        map.get(3);
        map.getOrDefault(42, "not fund");
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);

    }

}
