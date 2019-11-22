package com.chaochaogu.java8.test.streams;

import com.chaochaogu.java8.method.reference.Bar;
import com.chaochaogu.java8.method.reference.Foo;
import com.chaochaogu.java8.method.reference.Person;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
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

        Stream.of(1.0, 2.0, 3.0)
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
        Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.getAge()));
        personsByAge.forEach((age, p) -> System.out.println("age: " + age + p));
        persons.stream().collect(Collectors.averagingInt(p -> p.getAge()));
        IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.getAge()));
        String phrase = persons
                .stream()
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getName())
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
        persons.stream().collect(Collectors.toMap(
                p -> p.getAge(),
                p -> p.getName(),
                (name1, name2) -> (name1 + ";" + name2)
        ));
        
        // groupingBy and sum
        Map<String, Integer> map = Stream.of(
                new Person("周杰伦", 40),
                new Person("林俊杰", 38),
                new Person("林宥嘉", 32),
                new Person("小明", 23),
                new Person("小明", 24),
                new Person("小红", 11),
                new Person("小红", 12)
        ).collect(Collectors.groupingBy(Person::getName, Collectors.summingInt(Person::getAge)));

        // flatMap
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        // reduce
        persons.stream()
                .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
                .ifPresent(System.out::println);
        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.getAge(), (sum1, sum2) -> sum1 + sum2);

        // parallel Stream
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));


    }

}
