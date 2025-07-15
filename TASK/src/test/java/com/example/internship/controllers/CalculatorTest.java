package com.example.internship.controllers;

import com.example.internship.Controller.Calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public  void testAddition(){
        Calculator adds = new Calculator();
        int s = adds.add(5,3);
        System.out.println(s);
        assertEquals(8 , s);
    }
    @Test
    public  void testSubtractions(){
        Calculator sub = new Calculator();
        int x = sub.subtract(50,30);
        System.out.println(x);
        assertEquals( 20, x);
    }
    @Test
    public  void testMultiplication(){
        Calculator multiply = new Calculator();
        int y= multiply.multiplication(5,3);
        System.out.println(y);
        assertEquals(15, y);
    }
    @Test
    public  void testDivision(){
        Calculator divide = new Calculator();
        int d = divide.division(20,4);
        System.out.println(d);
        assertEquals(5 , d);
    }
    @Test
    public  void testModulo(){
        Calculator mod = new Calculator();
        int m= mod.modulo(16,3);
        System.out.println(m);
        assertEquals( 1, m);
    }
}