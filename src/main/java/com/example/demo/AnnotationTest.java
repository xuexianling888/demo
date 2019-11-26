package com.example.demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @program: test
 * @description: 自定义注解实现工厂设计模式与动态代理
 * @author: laoXue
 * @create: 2019-11-26 10:41
 **/
public class AnnotationTest {
    public static void main(String[] args) {
        ServiceTest serviceTest = new ServiceTest();
        serviceTest.send("Hello World");
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Annocation1 {
    Class<?> clazz();
}

@Annocation1(clazz = MessageImpl.class)
class ServiceTest {
    private Imessage imessage;

    public ServiceTest() {
        Annocation1 annotation = ServiceTest.class.getAnnotation(Annocation1.class);
        Class<?> clazz = annotation.clazz();
        Imessage imessage = (Imessage) factory.getInstance(clazz);
        this.imessage = imessage;
    }

    public void send(String msg) {
        this.imessage.send(msg);
    }
}

class factory {
    private factory() {
    }

    public static <T> T getInstance(Class<T> clazz) {
        try {
            T o = clazz.getDeclaredConstructor().newInstance();
            return (T) new ProxyMessage().bind(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}