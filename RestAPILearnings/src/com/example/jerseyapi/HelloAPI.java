package com.example.jerseyapi;


import com.example.model.Student;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")
public class HelloAPI {
    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_HTML)
    public String getString() {
        return "Getting response from getString() method of jersey API";
    }

    @GET
    @Path("/get/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentByName(@PathParam("name") String name) {
        System.out.println(name);
        return Student.getStudentByName(name);
    }
    @GET
    @Path("/math/{num}")
    @Produces(MediaType.TEXT_HTML)
    public String doMathOperation(@PathParam("num") int num) {
        System.out.println(num);
        int ans = 5/num;
        return String.valueOf(ans);
    }
}
