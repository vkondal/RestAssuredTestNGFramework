package com.spotify.oauth2.utils;

public class test {
    public static void main(String[] args) {
      ConfigLoader obj1=  ConfigLoader.createInstance();
      ConfigLoader obj2=  ConfigLoader.createInstance();
        System.out.println(obj1 + "\n" + obj2);
        singletonTest s1 = singletonTest.getInstance();
        singletonTest s2 = singletonTest.getInstance();

        System.out.println(s1 + "\n" + s2);

    }
}
