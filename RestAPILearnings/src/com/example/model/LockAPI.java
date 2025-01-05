package com.example.model;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NameBinding
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

/**
 * Add this annotation the API methods which need to execute after the interval specified.
 * Ex : if this is added to API /api/v2 , then this can be accessed only 1 time in a specified seconds
 * Then they have to wait for specified seconds after they access.
 */
public @interface LockAPI {
    // we can even add methods here such that they can declare the value when they annotate this to a method!
    int lockSeconds() default 60;
}
