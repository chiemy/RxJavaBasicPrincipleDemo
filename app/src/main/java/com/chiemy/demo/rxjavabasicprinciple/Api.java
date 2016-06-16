package com.chiemy.demo.rxjavabasicprinciple;

import java.util.List;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public interface Api {

    interface PersonQueryCallback {

        void onPersonsReceived(List<Person> persons);

        void onError(Exception e);
    }

    interface AddFriendResultCallback{
        void onAddResult(Result result);

        void onError(Exception e);
    }

    void queryPerson(PersonQueryCallback callback);

    void addFirend(Person person, AddFriendResultCallback callback);
}
