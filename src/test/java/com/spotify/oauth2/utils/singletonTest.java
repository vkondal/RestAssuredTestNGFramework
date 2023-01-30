package com.spotify.oauth2.utils;

public class singletonTest {
    private static singletonTest singletonTest;

    private singletonTest(){
    }

    public static singletonTest getInstance(){
        if (singletonTest ==null){
            singletonTest = new singletonTest();
        }
        return singletonTest;
    }
}
