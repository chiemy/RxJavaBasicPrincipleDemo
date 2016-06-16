package com.chiemy.demo.rxjavabasicprinciple;

/**
 * Created: chiemy
 * Date: 16/6/16
 * Description:
 */
public interface Func<T, R> {
    /**
     * 将 T转换为 R
     * @param t
     * @return
     */
    R call(T t);
}
