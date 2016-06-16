package com.chiemy.demo.rxjavabasicprinciple;

import java.util.List;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public interface Api {

    List<Person> queryPerson();

    Result addFirend(Person person);
}
