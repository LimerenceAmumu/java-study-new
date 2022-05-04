package com.lhp.faker;

import net.datafaker.Address;
import net.datafaker.Faker;
import org.junit.Test;

import java.util.Locale;

/**
 * Faker Data
 *
 * @Description:
 * @author: lihp
 * @date: 2022/4/25 17:16
 */
public class Demo1 {
    @Test
    public void test1() {
        Faker faker = new Faker(new Locale("zh"));
        Address address = faker.address();
        System.out.println("address = " + address.zipCodePlus4());
        System.out.println("faker.name().nameWithMiddle() = " + faker.name().nameWithMiddle());


    }

    @Test
    public void test2() {
        Faker faker = new Faker(new Locale("zh"));
        Address address = faker.address();
        System.out.println("address = " + address.zipCodePlus4());
        System.out.println("faker.name().nameWithMiddle() = " + faker.name().nameWithMiddle());


    }

}
