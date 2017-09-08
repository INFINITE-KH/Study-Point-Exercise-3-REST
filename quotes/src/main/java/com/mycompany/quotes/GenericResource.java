package com.mycompany.quotes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("quote")
public class GenericResource {

    private static Map<Integer,String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    @Context
    private UriInfo context;

    public GenericResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(("/get/{id}"))
    public Response getQuoteById(@PathParam("id")int id){
        return Response.status(Response.Status.NOT_FOUND).entity("Person not found for id :" + id).build();
    };
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(("/get/random"))
    public String getRandomQuetes(){
        Random random = new Random();
        int randomId = random.nextInt(3-1+1)+1;
        return quotes.get(randomId);
    };
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(("/create/{quote}"))
    public String postJasonQuote(@PathParam("quote")String quote){
        System.out.println(quote);
        int id = quotes.size() +1;
        quotes.put(id, quote);    
        return "{\"quote\": \"" + quote + "\"}";
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(("/update/{id}/{quote}"))
    public String updateQuote(@PathParam("id")int id, @PathParam("quote")String quote){
        quotes.put(id, quote);
        return " "+quotes.get(id)+" ";  
    };
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path(("/delete/{id}"))
    public String deleteQuote(@PathParam("id")int id){
        quotes.remove(id);
        return "deleted";  
    };
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
