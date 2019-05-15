package com.dekses.jersey.docker.demo;

import DB.DB_Requests;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.protobuf.TextFormat.ParseException;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.ThImage;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

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
