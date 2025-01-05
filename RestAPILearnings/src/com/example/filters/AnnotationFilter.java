package com.example.filters;

import com.example.model.LockAPI;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AnnotationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    Hashtable<String,Boolean> lockingFor1Min = new Hashtable<>();
    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        // similarly we can add ContainerResponseContext too so that all the response comes here and , we can do
        // logging OR data masking OR tracking stuff.

        Method resourceMethod = resourceInfo.getResourceMethod();

        // This is just an example how an annotation play a role in filters. We can put those annotations
        // in a special API such that the API has been restricted to a functionality / acts as a dependency check
        // even we can restrict the user to access such APIs

        String methodName = resourceMethod.getName();

        LockAPI premiumOnly = resourceMethod.getAnnotation(LockAPI.class);
        System.out.println("Method to be called -> "+methodName);

        if(premiumOnly == null) {
            System.out.println("API is not for premium customers ...");
        } else {
            // need to check with our custom logic.
            // Only 1 time the premiumOnly API can be accessed.
            if(lockingFor1Min.getOrDefault(methodName,false)) {
                containerRequestContext.abortWith(Response.status(Response.Status.SERVICE_UNAVAILABLE).build());
            } else {
                lockingFor1Min.put(methodName, true);
                releaseLockAfter1Min(methodName);
            }
        }
    }
    private void releaseLockAfter1Min(String methodName) {
        executor.schedule(() -> {
            System.out.println("Going to unlock method :: "+methodName);
            lockingFor1Min.put(methodName, false);
            System.out.println("Method Unlocked .");
        },1 , TimeUnit.MINUTES);
        System.out.println("Scheduled to unlock the method after 1 minute  :: "+methodName);
    }

    static {
        registerShutDownHook();
    }

    private static void registerShutDownHook() {
        try {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                // kills all the thread before stopping application if any.
                executor.shutdownNow();
            }));
        } catch (Exception ignored) {

        }
    }

}
