package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

public class VolatileTest {

    @Test
    public void test() {
        Integer var0 = 33;
        Integer var1 = 331;
        Assert.assertEquals("不相等", var0, var1);
    }
}
