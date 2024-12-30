package com.example.jerseyapi;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class APIApplicationLoader extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        // add Rest API classes below
        classSet.add(com.example.jerseyapi.extendedAPI.HelloExtnAPI.class);

        return classSet;
    }
}
