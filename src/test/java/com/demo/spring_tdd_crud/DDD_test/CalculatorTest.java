package com.demo.spring_tdd_crud.DDD_test;

import com.demo.spring_tdd_crud.DDD_model.Calculator;
import com.demo.spring_tdd_crud.DDD_model.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;


    @BeforeEach
    void setUp(){
        calculator = new Calculator();
    }

    @Test
    void multiplyTwoByTwoShouldReturnFour(){
        assertEquals(4 , calculator.calculate(2 , 2 , (x , y) -> x * y));
    }

    @Test
    void twoPlusFiveShouldReturnSeven(){
        assertEquals(7 , calculator.calculate(2 , 5 , Integer::sum));
    }

    @Test
    void tenDividedByTwoShouldReturnFive(){
        assertEquals(5 , calculator.calculate(10 , 2 , (x , y) -> x / y));
    }

}
