package org.example;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        // Once we add dependency in pom.xml, just
        // call mvn clean , it will pull the files from mvnrepository

        // now we can use those code here.
        JSONObject object = new JSONObject("{\"abc\" : \"value\"}");
        System.out.println(object.toString(4));
    }
}