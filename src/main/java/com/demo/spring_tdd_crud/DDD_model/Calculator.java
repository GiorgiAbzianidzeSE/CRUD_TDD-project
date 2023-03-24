package com.demo.spring_tdd_crud.DDD_model;

public class Calculator {

    public <E> E calculate(E e1 , E e2 , Operator<E> operator){
        return operator.operate(e1 , e2);
    }
}
