package com.dekses.jersey.docker.demo;

import java.io.IOException;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ResourceConfig;
/**
 * Main class.
 *
 */
public class Main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:3000/myapp/";

    public static void main(String[] args) {
        ResourceConfig resourceConfig = new MyResourceConfig();
        try {
            GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
            System.out.println(String.format("Jersey app started with WADL available at "
                    + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
