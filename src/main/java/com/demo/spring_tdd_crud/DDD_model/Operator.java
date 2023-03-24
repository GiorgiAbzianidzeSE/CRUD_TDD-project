package com.demo.spring_tdd_crud.DDD_model;

@FunctionalInterface
public interface Operator<T> {
    T operate(T p1 , T p2);
}
