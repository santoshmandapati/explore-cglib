package com.sandyprojects;

import net.sf.cglib.beans.BeanGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanGeneratorTest {
    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "value set by cglib");

        Method getter = myBean.getClass().getMethod("getName");
        Assert.assertEquals("value set by cglib", getter.invoke(myBean));
    }
}
