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

import com.ejemplo.Rest_AndresVillani_DanielGil.Entities.Customer;
import com.ejemplo.Rest_AndresVillani_DanielGil.Entities.OrderDetail;
import com.ejemplo.Rest_AndresVillani_DanielGil.Models.CustomersModel;
import com.ejemplo.Rest_AndresVillani_DanielGil.Models.OrderDetailsModel;

@Path("clientes")
public class OrderDetailRest {
    static OrderDetailsModel OrdersDetails;

    public OrderDetailRest() {

	try {
	    OrdersDetails = new OrderDetailsModel();
	} catch (SQLException e) {
	    System.err.println("No puedo abrir la conexion con 'OrdersDetails': " + e.getMessage());
	}
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("filter") String filter, 
	                 @QueryParam("limit") Integer limit, 
	                 @QueryParam("offset") Integer offset) {
	Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
	
	if (OrdersDetails != null) {
	    ArrayList<OrderDetail> listaDetallesPedidos = OrdersDetails.lista(filter, limit, offset);
	    if (listaDetallesPedidos != null) {
		respuesta = Response.status(Response.Status.OK).entity(listaDetallesPedidos).build();
	    }

	}
	return respuesta;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Integer id) {
	
	Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No he encotrado").build();
	
	if (OrdersDetails != null) {
		OrderDetail DetallesPedido = OrdersDetails.read(id);
	    if (DetallesPedido != null) {
		respuesta = Response.status(Response.Status.OK).entity(DetallesPedido).build();
	    }
	}
	return respuesta;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(OrderDetail DetallesPedido) {
	Integer idDetallesPedido;
	Response response;
	try {
	    idDetallesPedido = OrdersDetails.insert(DetallesPedido);
	    response = Response.status(Response.Status.CREATED).entity(idDetallesPedido).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.CONFLICT).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(OrderDetail PedidoDetalles) {
	Boolean PedidoActualizado;
	Response response;
	try {
	    PedidoActualizado = OrdersDetails.update(PedidoDetalles);
	    response = Response.status(Response.Status.OK).entity(PedidoActualizado).build();
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
	Boolean PedidoActualizado;
	Response response;
	try {
	    PedidoActualizado = OrdersDetails.delete(id);
	    response = Response.status(Response.Status.OK).entity(PedidoActualizado).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.NOT_FOUND).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }

    
}
