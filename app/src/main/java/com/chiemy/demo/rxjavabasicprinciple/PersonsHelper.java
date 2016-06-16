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

    public void addFriends(final String name, final Callback<Boolean> callback){
        api.queryPerson(new Callback<List<Person>>() {
            @Override
            public void onResult(List<Person> result) {
                Person person = filterPerson(result, name);
                api.addFirend(person, callback);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
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
