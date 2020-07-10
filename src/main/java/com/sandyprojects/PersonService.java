package com.sandyprojects;

public class PersonService {
    public String greet(String name) {
        return "Hello, " + name;
    }

    public Integer lengthOfName(String name) {
        return name.length();
    }
}
