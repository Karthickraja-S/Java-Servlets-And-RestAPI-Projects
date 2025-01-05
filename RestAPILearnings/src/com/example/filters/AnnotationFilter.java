package com.example.filters;

import com.example.model.LockAPI;
import com.example.model.LockModel;

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
import java.util.logging.Logger;

public class AnnotationFilter implements ContainerRequestFilter {

    Logger logger = Logger.getLogger(AnnotationFilter.class.getName());
    @Context
    private ResourceInfo resourceInfo;
    Hashtable<String, LockModel> lockMap = new Hashtable<>();
    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        // similarly we can add ContainerResponseContext too so that all the response comes here and , we can do
        // logging OR data masking OR tracking stuff.

        Method resourceMethod = resourceInfo.getResourceMethod(); // we can access the class too.

        // This is just an example how an annotation play a role in filters. We can put those annotations
        // in a special API such that the API has been restricted to a functionality / acts as a dependency check
        // even we can restrict the user to access such APIs

        String methodName = resourceMethod.getName();

        LockAPI lockAPI = resourceMethod.getAnnotation(LockAPI.class);
        System.out.println("Method to be called -> "+methodName);

        if(lockAPI == null) {
            System.out.println("API is not restricted ...");
        } else {
            int secondsNeedToDelay = lockAPI.lockSeconds();
            int apiAllowedToExecute      = lockAPI.apiAllowedToExecute();

            // need to check whether the api can be allowed.
            if(lockMap.containsKey(methodName) &&
                    ( lockMap.get(methodName).isLocked() ) ) {
                containerRequestContext.abortWith(Response.status(Response.Status.SERVICE_UNAVAILABLE).build());
            } else {
                // check whether the method already has Lock Obj , If so decrement the allowedCount and if reaches 0 then lockIt
                if(lockMap.containsKey(methodName)) {
                    LockModel model = lockMap.get(methodName);
                    System.out.println(model);
                    if(model.getMethodAllowedToExecute() == 1) {
                        model.setLocked(true);
                        resetLock(methodName , secondsNeedToDelay);
                    } else {
                        model.setMethodAllowedToExecute(model.getMethodAllowedToExecute() - 1);
                    }
                } else {
                    lockMap.put(methodName, new LockModel(false, apiAllowedToExecute - 1));
                }
            }
        }
    }
    private void resetLock(String methodName , int seconds) {
        executor.schedule(() -> {
            logger.info("Going to unlock method :: "+methodName);
            lockMap.remove(methodName);
            logger.info("Method Unlocked .");
        },seconds , TimeUnit.SECONDS);
        logger.info("Scheduled to unlock the method "+ methodName+" after seconds :: "+seconds);
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
