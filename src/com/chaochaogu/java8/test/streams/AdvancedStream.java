package com.chaochaogu.java8.test.streams;

import com.chaochaogu.java8.method.reference.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author chaochao Gu
 * @date 2019/8/7
 */
public class AdvancedStream {

    public static void main(String[] args) {

        Stream.of("q1", "q2", "q3").findFirst().ifPresent(System.out::println);

        IntStream.range(1, 4).forEach(System.out::println);

        Arrays.stream(new int[]{1, 2, 3, 4, 5})
                .map(n -> n * 2 + 1)
                .average()
                .ifPresent(System.out::println);

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        IntStream.of(1, 2, 3)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        Stream.of(1.0, 2.0 ,3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "b" + i)
                .forEach(System.out::println);

        // intermediate operations will only be executed when a terminal operation is present
        Stream.of("s1", "c1", "r2", "f3")
                .filter((s) -> {
                    System.out.println("filter" + s);
                    return true;
                });

        // Processing Order
        Stream.of("s1", "c1", "r2", "f3")
                .filter((s) -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach((s) -> {
                    System.out.println("forEach: " + s);
                });

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map((s) -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch((s) -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map((s) -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter((s) -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter((s) -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map((s) -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                }).forEach(s -> System.out.println("foreach: " + s));

        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        // Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed
        Stream<String> stringStream = Stream.of("d2", "a2", "b1", "b3", "c")
                .filter((s) -> s.startsWith("a"));
        stringStream.anyMatch(s -> true);
        stringStream.allMatch(s -> false);

        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().allMatch(s -> false);

        // Advanced Operations
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        List<Person> filtered = persons.stream().filter(p -> p.getName().startsWith("p")).collect(Collectors.toList());


    }

}
