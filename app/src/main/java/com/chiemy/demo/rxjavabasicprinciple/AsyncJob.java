package com.chiemy.demo.rxjavabasicprinciple;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);
}
