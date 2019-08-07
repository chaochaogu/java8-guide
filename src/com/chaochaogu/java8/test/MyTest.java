package com.chaochaogu.java8.test;

import com.chaochaogu.java8.impl.MyInterfaceImpl;
import com.chaochaogu.java8.myInterface.MyInterface;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author chaochao Gu
 * @date 2019/8/2
 */
public class MyTest {

    public static void main(String[] args) {

        MyInterfaceImpl myInterface = new MyInterfaceImpl();
        MyInterface myInterface1 = ()->{
            System.out.println("lamda eating!!");
        };
        long l = System.currentTimeMillis();
        System.out.println(l);
        Year now = Year.now();
        System.out.println(now);
        Instant now1 = Instant.now();
        LocalDate now2 = LocalDate.now();
        LocalDateTime now3 = LocalDateTime.now();
        double res = 1 / 3.0;
        BigDecimal.valueOf(res);
        System.out.println(res);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = "1998-09-09";
        LocalDate date = LocalDate.now();
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        String s = formatter.format(date);
        List list = Collections.EMPTY_LIST;
        list.add(1);


    }
}
