package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        ApplicationContext Context = new ClassPathXmlApplicationContext("Spring.XML");
        Alien Obj = (Alien) Context.getBean("Alien");
        System.out.println("The age is :- " + Obj.getAge());
        Obj.code();
    }
}