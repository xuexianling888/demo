package com.example.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: test
 * @description:动态代理
 * @author: laoXue
 * @create: 2019-11-26 10:07
 **/
public class ProxyTest {
    public static void main(String[] args) {
        Imessage bind = (Imessage) new ProxyMessage().bind(new MessageImpl());
        bind.send("Hello World");
    }
}

interface Imessage {
    void send(String msg);
}

class MessageImpl implements Imessage {
    @Override
    public void send(String msg) {
        System.out.println("发送信息： " + msg);
    }
}

class ProxyMessage implements InvocationHandler {
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    public boolean connente() {
        System.out.println("连接.........");
        return true;
    }

    public void close() {
        System.out.println("关闭连接......");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            if (this.connente()) {
                method.invoke(this.target, args);
            } else {
                System.out.println("连接失败");
            }
        } catch (Exception exp) {
            System.out.println("发送信息失败");
        } finally {
            this.close();
        }
        return null;
    }
}