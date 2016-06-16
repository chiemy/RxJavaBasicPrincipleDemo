package com.chiemy.demo.rxjavabasicprinciple;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person another) {
        if (age > another.age) {
            return 1;
        } else if (age < another.age) {
            return -1;
        } else {
            return 0;
        }
    }
}
