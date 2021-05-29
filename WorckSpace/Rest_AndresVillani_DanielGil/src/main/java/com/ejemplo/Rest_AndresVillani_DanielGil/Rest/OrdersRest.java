package com.ejemplo.Rest_AndresVillani_DanielGil.Rest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejemplo.Rest_AndresVillani_DanielGil.Entities.Order;
import com.ejemplo.Rest_AndresVillani_DanielGil.Models.OrdersModel;

@Path("ordenes")
public class OrdersRest {
    static OrdersModel orders;

    public OrdersRest() {

	try {
	    orders = new OrdersModel();
	} catch (SQLException e) {
	    System.err.println("No puedo abrir la conexion con 'Orders': " + e.getMessage());
	}
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("filter") String filter, 
	                 @QueryParam("limit") Integer limit, 
	                 @QueryParam("offset") Integer offset) {
	Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
	if (orders != null) {
	    ArrayList<Order> listaOrdenes = orders.lista(filter, limit, offset);
	    if (listaOrdenes != null) {
		respuesta = Response.status(Response.Status.OK).entity(listaOrdenes).build();
	    }

	}
	return respuesta;
    }

    @GET
    @Path("/editar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Integer id) {
	
	Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No he encotrado").build();
	
	if (orders != null) {
	    Order ordenes = orders.read(id);
	    if (ordenes != null) {
		respuesta = Response.status(Response.Status.OK).entity(ordenes).build();
	    }
	}
	return respuesta;
    }
    
    @GET
    @Path("/cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCustomer(@PathParam("id") Integer id, 
	                 @QueryParam("limit") Integer limit, 
	                 @QueryParam("offset") Integer offset) {
	Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
	if (orders != null) {
	    ArrayList<Order> listaOrdenes = orders.listaCustomer(id, limit, offset);
	    if (listaOrdenes != null) {
		respuesta = Response.status(Response.Status.OK).entity(listaOrdenes).build();
	    }

	}
	return respuesta;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Order ordenes) {
	Integer idOrdenes;
	Response response;
	try {
	    idOrdenes = orders.insert(ordenes);
	    response = Response.status(Response.Status.CREATED).entity(idOrdenes).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.CONFLICT).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Order ordenes) {
	Boolean ordenActualizado;
	Response response;
	try {
	    ordenActualizado = orders.update(ordenes);
	    response = Response.status(Response.Status.OK).entity(ordenActualizado).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.NOT_MODIFIED).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
	Boolean ordenActualizado;
	Response response;
	try {
	    ordenActualizado = orders.delete(id);
	    response = Response.status(Response.Status.OK).entity(ordenActualizado).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.NOT_FOUND).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }

    
}
