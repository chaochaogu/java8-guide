package com.chaochaogu.java8.test.built.in.interfaces;

import com.chaochaogu.java8.method.reference.Person;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author chaochao Gu
 * @date 2019/8/6
 */
public class BuiltInInterfaces {

    public static void main(String[] args) {

        // Predicate
        Predicate<String> predicate = s -> s.length() > 0;
        predicate.test("foo");
        predicate.negate().test("foo");
        Predicate<Object> nonNull = Objects::nonNull;
        Predicate<Object> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // Function
        Function<String, Integer> function = (s) -> Integer.valueOf(s);
        Function<String, String> andThen = function.andThen((in) -> String.valueOf(in));
        andThen.apply("123");
        Function<Object, Integer> compose = function.compose((in) -> in + "23");
        System.out.println(compose.apply(45));

        // Supplier
        Supplier<String> supplier = () -> "233";
        supplier.get();
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

        // Consumer
        Consumer<String> consumer = (s)->{};
        consumer.andThen((s) -> s = s + "122");
        consumer.accept("334");
        Consumer<Person> greeter = (p) -> System.out.println("my name is" + p.getName());
        greeter.accept(new Person(1,"张三"));

        // Comparator
        Comparator<Person> comparator = (p1, p2) -> (p1.getName().compareTo(p2.getName()));
        Person jack = new Person(1, "Jack");
        Person rose = new Person(2, "Rose");
        int compare = comparator.compare(jack, rose);
        System.out.println(compare);

        // Optional
        Optional<String> option = Optional.of("bam");
        option.isPresent();
        option.get();
        option.orElse("not exist");
        option.ifPresent((s) -> System.out.println(s.charAt(0)));


    }
}
