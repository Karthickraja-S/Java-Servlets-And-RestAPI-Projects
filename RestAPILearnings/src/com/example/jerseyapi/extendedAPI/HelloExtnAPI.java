package com.example.jerseyapi.extendedAPI;

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
}
