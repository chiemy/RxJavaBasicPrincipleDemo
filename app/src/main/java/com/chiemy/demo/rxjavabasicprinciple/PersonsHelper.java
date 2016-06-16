package com.chiemy.demo.rxjavabasicprinciple;

import android.text.TextUtils;

import java.util.List;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public class PersonsHelper {
    private Api api;

    public boolean addFriends(final String name){
        List<Person> persons = api.queryPerson();
        Person person = filterPerson(persons, name);
        return api.addFirend(person).isSuccess();
    }

    private Person filterPerson(List<Person> persons, String name){
        int size = persons.size();
        Person targetPerson = null;
        for (int i = 0 ; i < size ; i++){
            Person person = persons.get(i);
            if (TextUtils.equals(person.getName(), name)){
                targetPerson = person;
            }
        }
        return targetPerson;
    }

}
