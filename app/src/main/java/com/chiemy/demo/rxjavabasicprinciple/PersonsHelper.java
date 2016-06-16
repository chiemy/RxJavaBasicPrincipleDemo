package com.chiemy.demo.rxjavabasicprinciple;

import android.text.TextUtils;

import java.util.List;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public class PersonsHelper {
    private ApiWrapper api;

    public AsyncJob<Boolean> addFriends(final String name){
        final AsyncJob<List<Person>> queryJob = api.queryPerson();
        final AsyncJob<Person> filterPersonJob = queryJob.map(new Func<List<Person>, Person>() {

            @Override
            public Person call(List<Person> persons) {
                return filterPerson(persons, name);
            }
        });

        AsyncJob<Boolean> resultJob = filterPersonJob.flatmap(new Func<Person, AsyncJob<Boolean>>(){

            @Override
            public AsyncJob<Boolean> call(Person person) {
                return api.addFirend(person);
            }
        });

        return resultJob;
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
