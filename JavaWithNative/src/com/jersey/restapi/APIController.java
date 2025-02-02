package com.jersey.restapi;


import com.nativecode.impl.NativeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1")
public class APIController {

    private static final NativeService service = new NativeService();

    @GET()
    @Path("/test")
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
    //    System.out.println(System.getProperties());
        return "Hi..";
    }
    @GET()
    @Path("/native")
    @Produces(MediaType.TEXT_HTML)
    public String helloNative() {
        return service.getMsgFromNative();
    }
}
