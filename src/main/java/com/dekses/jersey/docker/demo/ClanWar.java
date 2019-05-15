package com.dekses.jersey.docker.demo;

import DB.DB_Requests;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Miembro;

import model.War;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@Path("/ClanWar")
public class ClanWar {

    // URI:
    // /contextPath/servletPath/Clan
    // URI:
    // /contextPath/servletPath/Clan/{empNo}
    @GET

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String getEmployee() throws IOException, SQLException {

        HttpClient httpclient = HttpClientBuilder.create().build();  // the http-client, that will send the request
        HttpGet httpGet = new HttpGet("https://api.clashofclans.com/v1/clans/%23202C8RGUC/currentwar");   // the http GET request
        httpGet.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijk4NjM3ZmI3LWNiMmItNDJkMy05NmZkLWRkZjA0OGQ1YjM2NyIsImlhdCI6MTU1NzYwODg4NSwic3ViIjoiZGV2ZWxvcGVyLzY0NWYwMzUyLTQ1YWEtZmI5Yy0zNGY5LTNjMmYxNjU1NmU0YyIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE4Ni4zMC45NS4xNjQiXSwidHlwZSI6ImNsaWVudCJ9XX0.vVOrSSivPvNeGLQDR6EcnpEkbBDrum3FCZwhV6Q3bE5Zk-EuUgHrkNnuA1iq0LqEypHsUsiw1itzIqD_g2TrSA"); // add the authorization header to the request
        HttpResponse response = httpclient.execute(httpGet); // the client executes the request and gets a response
        int responseCode = response.getStatusLine().getStatusCode();
        String stringResponse = EntityUtils.toString(response.getEntity());  // now you have the response as String, which you can convert to a JSONObject or do other stuff
            String Miembros="";
            String State="";
        switch (responseCode) {
            case 200: {
                // everything is fine, handle the response

                Gson gson = new Gson();
                War war = gson.fromJson(stringResponse, War.class);
                DB_Requests db_Requests = new DB_Requests();
                State = db_Requests.ClanWarState(war);
                ArrayList<Miembro> list = db_Requests.Miembros();
                Miembros = "";
                while (!list.isEmpty()) {
                    int index = 0;
                    int v = list.get(0).getPuntos();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPuntos() > v) {
                            index = i;
                            v = list.get(i).getPuntos();
                        }

                    }

                    Miembros+="\n"+list.remove(index)+"\n";

                }
                break;
            }
            case 500: {
                // server problems ?
                break;
            }
            case 403: {
                // you have no authorization to access that resource
                break;
            }
        }

        return State+"\n"+Miembros+"";
    }
}
