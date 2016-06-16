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

    public interface AddFriendCallback {
        void onResult(boolean success);

        void onError(Exception e);
    }

    public void addFriends(final String name, final AddFriendCallback callback){
        // 虽然改为了异步,不会阻塞线程,但跟之前的代码相比更复杂了
        // 对于每个异步操作,我们需要手动插入回调
        // 错误传递,看onError部分,每个onError都需要我们手动插入回调代码
        api.queryPerson(new Api.PersonQueryCallback() {
            @Override
            public void onPersonsReceived(List<Person> persons) {
                Person person = filterPerson(persons, name);
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
