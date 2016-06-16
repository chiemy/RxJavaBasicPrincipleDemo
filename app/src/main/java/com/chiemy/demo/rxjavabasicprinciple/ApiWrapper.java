package com.chiemy.demo.rxjavabasicprinciple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public class ApiWrapper {
    private Api api;

    public ApiWrapper(){
        // 测试
        api = new Api() {
            @Override
            public void queryPerson(PersonQueryCallback callback) {
                List<Person> persons = new ArrayList<>();
                for (int i = 0 ; i < 3 ; i++){
                    Person person = new Person();
                    person.setName(i + "");
                    person.setAge(i);
                    persons.add(person);
                }
                callback.onPersonsReceived(persons);
            }

            @Override
            public void addFirend(Person person, AddFriendResultCallback callback) {
                if (person == null) {
                    callback.onError(new Exception("参数为null"));
                    return;
                }
                String name = person.getName();
                Result result = new Result();
                if (Integer.parseInt(name) >= 0 && Integer.parseInt(name) < 3) {
                    result.setSuccess(true);
                } else {
                    result.setSuccess(false);
                }
                callback.onAddResult(result);
            }
        };
    }

    AsyncJob<List<Person>> queryPerson(){
        return new AsyncJob<List<Person>>() {
            @Override
            public void start(final Callback<List<Person>> callback) {
                api.queryPerson(new Api.PersonQueryCallback(){

                    @Override
                    public void onPersonsReceived(List<Person> persons) {
                        callback.onResult(persons);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }

    AsyncJob<Boolean> addFirend(final Person person){
        return new AsyncJob<Boolean>() {
            @Override
            public void start(final Callback<Boolean> callback) {
                api.addFirend(person, new Api.AddFriendResultCallback() {
                    @Override
                    public void onAddResult(Result result) {
                        callback.onResult(result.isSuccess());
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }
}
