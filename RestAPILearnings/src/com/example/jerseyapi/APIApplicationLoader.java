package com.example.jerseyapi;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class APIApplicationLoader extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        // add Rest API classes below
        classSet.add(com.example.jerseyapi.extendedAPI.HelloExtnAPI.class);
        
        // all the class registered ( apis ) will also be sent to this filters
        // also we can add mult filters
        classSet.add(com.example.filters.AnnotationFilter.class);

        return classSet;
    }
}
