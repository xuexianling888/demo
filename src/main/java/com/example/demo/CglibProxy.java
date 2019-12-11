package com.example.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: demo
 * @description: CGLIB动态代理
 * @author: laoXue
 * @create: 2019-12-11 10:44
 **/
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        //生成指定类对象的子类,也就是重写类中的业务函数，在重写中加入intercept()函数而已。
        enhancer.setSuperclass(clazz);
        //这里是回调函数，enhancer中肯定有个MethodInterceptor属性。
        //回调函数是在setSuperclass中的那些重写的方法中调用---猜想
        enhancer.setCallback(this);
        //创建这个子类对象
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println(method.getName() + "执行之前做一些准备工作");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println(method.getName() + "执行之后做一些准备的工作");
        return result;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        UserManager um = (UserManager) proxy.getProxy(UserManager.class);
        um.function();
    }
}

class UserManager {

    public void function() {
        System.out.println("如果我只停留在使用的别人开发的工具阶段，那么再过5年我也对不起程序员这个称呼");
    }
}