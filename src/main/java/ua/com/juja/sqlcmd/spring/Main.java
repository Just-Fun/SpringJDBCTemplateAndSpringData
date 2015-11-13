package ua.com.juja.sqlcmd.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by oleksandr.baglai on 13.11.2015.
 */
public class Main {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"application-context.xml"});

        LabRat rat = (LabRat) context.getBean("rat");
        rat.sayHi();
        System.out.println(rat.getName());

//        LabRat rat = new LabRat("Hi!");
//        rat.setName("Alice!");
//        rat.sayHi();
//        System.out.println(rat.getName());
    }

}