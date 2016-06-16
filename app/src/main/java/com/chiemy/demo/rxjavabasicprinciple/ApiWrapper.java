package com.chiemy.demo.rxjavabasicprinciple;

import java.util.List;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public class ApiWrapper {
    private Api api;

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
