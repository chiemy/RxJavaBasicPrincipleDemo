package com.chiemy.demo.rxjavabasicprinciple;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public interface Callback<T> {
    void onResult(T result);

    void onError(Exception e);
}
