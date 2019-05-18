package com.dekses.jersey.docker.demo;

import DB.DB_Requests;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.ThImage;


@Path("/ThImage")
public class ThImageS {

  
    // URI:
    // /contextPath/servletPath/Clan

   

    // URI:
    // /contextPath/servletPath/Clan/{empNo}
    @GET
    @Path("/{Lvl}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String getEmployee(@PathParam("Lvl") String Lvl) throws IOException {
        DB_Requests DB = new DB_Requests();
        try {
            ThImage thImage = new ThImage();
            thImage = DB.ThUrl(Integer.valueOf(Lvl));
            return thImage.getUrl();

        } catch (Exception e) {
            return "Error " + e;
        }

    }

  

}
