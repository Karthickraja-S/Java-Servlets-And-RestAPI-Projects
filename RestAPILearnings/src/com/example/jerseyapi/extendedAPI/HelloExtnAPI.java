package com.example.jerseyapi.extendedAPI;

import com.example.model.LockAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("v2")
public class HelloExtnAPI {
    @GET
    @Path("/getV2")
    @Produces(MediaType.TEXT_HTML)
    public String getStringV2() {
        return "Getting response from getString() method of jersey version2 API";
    }

    @GET
    @Path("/getV2/premium")
    @Produces(MediaType.TEXT_HTML)
    @LockAPI
    public String premiumAPIEx() {
        return "This is a premium API which can be used only one time. Come back after 60 seconds to access the API.";
    }

    @GET
    @Path("/getV2/premium2")
    @Produces(MediaType.TEXT_HTML)
    @LockAPI(lockSeconds = 30)
    public String premiumAPIEx2() {
        return "This is a premium 2 API which can be used only one time. Come back after 30 seconds to access the API.";
    }

}
