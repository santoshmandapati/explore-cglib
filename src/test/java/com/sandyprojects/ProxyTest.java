package com.sandyprojects;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonServiceTest {
    @Test
    public void test() {
        PersonService personService = new PersonService();
        assertEquals("Hello, Sandy", personService.greet("Sandy"));
    }

    @Test
    public void simpleProxyTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "Hello, Tom");

        PersonService proxyService = (PersonService) enhancer.create();
        assertEquals("Hello, Tom", proxyService.greet("Sandy"));
    }

    @Test
    public void methodInterceptorTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor)(obj, method, args, proxy) -> {
            if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello, Harry";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });

        PersonService proxyService = (PersonService) enhancer.create();
        assertEquals("Hello, Harry", proxyService.greet(null));
        int length = proxyService.lengthOfName("Tom");
        assertEquals(3, length);

    }
}
