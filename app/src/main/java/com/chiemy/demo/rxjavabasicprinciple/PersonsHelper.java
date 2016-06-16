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

        AsyncJob<Boolean> resultJob = new AsyncJob<Boolean>() {
            @Override
            public void start(final Callback<Boolean> callback) {
                filterPersonJob.start(new Callback<Person>() {
                    @Override
                    public void onResult(Person result) {
                        api.addFirend(result).start(new Callback<Boolean>() {
                            @Override
                            public void onResult(Boolean result) {
                                callback.onResult(result);
                            }

                            @Override
                            public void onError(Exception e) {
                                callback.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
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
