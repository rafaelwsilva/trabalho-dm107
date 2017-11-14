package br.inatel.pos.dm107.trabalho.service;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.inatel.pos.dm107.trabalho.model.ConnectionFactory;
import br.inatel.pos.dm107.trabalho.model.Entrega;
import br.inatel.pos.dm107.trabalho.model.EntregaDAO;

@Path("/apiLogistica")
public class EntregaService {

	@GET
	@Path("/entrega/{numPedido}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEntregaById(@PathParam("numPedido") int numPedido) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Entrega entrega = null;
		try {
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregaDAO = new EntregaDAO();
			entrega = entregaDAO.listByPedido(conn, numPedido);
			if (entrega == null) {
				return Response.status(Response.Status.NOT_FOUND).entity(entrega).build();
			} else {
				return Response.status(Response.Status.OK).entity(entrega).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/entrega/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Entrega entrega) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregaDAO = new EntregaDAO();
			entregaDAO.insert(conn, entrega);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
