package com.example.jerseyapi.extendedAPI;

import com.example.model.LockAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
        return "<h1> This is a premium API which can be used for 5 time within 60s. " +
                "Come back after 60 seconds to access the API. </h1>";
    }

    @GET
    @Path("/getV2/premium2")
    @Produces(MediaType.TEXT_HTML)
    @LockAPI(lockSeconds = 30 , apiAllowedToExecute = 3)
    public String premiumAPIEx2() {
        return "<h1> This is a premium2 API which can be used only 3 time. Come back after 30 seconds to access the API. </h1>";
    }

}
