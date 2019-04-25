package com.test;

public class Student {

    public void doSomething(){
        System.out.println("111111111");
    }

    public static void main(String[] args) {
        ((Student)null).doSomething();
    }
}
