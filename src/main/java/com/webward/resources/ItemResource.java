package com.webward.resources;


import com.google.inject.persist.Transactional;
import com.webward.dao.ItemDao;
import com.webward.dto.ItemDto;
import com.webward.entities.Item;
import com.webward.transformer.ItemTransformer;
import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/items")
@Singleton
public class ItemResource {

//    @Inject HelloWorld helloWord;
    @Inject
    EntityManager entityManager;
    @Inject
    ItemTransformer itemTransformer;
    @Inject
    ItemDao itemDao;

    @GET
    @Path("/itemById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDto getItemById(@PathParam("id") int id) {
//        System.out.println(entityManager);
        return itemTransformer.toDto(itemDao.findById(id));
    }

    @GET
    @Path("/itemByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDto getItemById(@PathParam("name") String aName) {
//        System.out.println(entityManager);
        return itemTransformer.toDto(itemDao.findByName(aName));
    }


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ItemDto createItem(ItemDto itemDto)
    {
        Item item = itemTransformer.fromDto(itemDto);
        itemDao.save(item);
        return itemTransformer.toDto(item);
    }

    @DELETE
    @Path("/deleteById/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") String id)
    {
        itemDao.deleteById(new Integer(id));
        return Response.status(204).build();
    }


    @GET
    @Path("basic")
    @Produces("text/plain")
    public void getBasic(final @Suspend(10000) AsynchronousResponse response) throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Response jaxrs = Response.ok("basic").type(MediaType.TEXT_PLAIN).build();
                    response.setResponse(jaxrs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }



}
