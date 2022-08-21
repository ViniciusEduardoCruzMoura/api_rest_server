package br.edu.ifms.clientrest.servico;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import br.edu.ifms.clientrest.dao.ClientDao;
import br.edu.ifms.clientrest.dao.ClientDaoImpl;
import br.edu.ifms.clientrest.modelo.Client;
import br.edu.ifms.clientrest.util.Conexao;

@Path("/client")
public class ClientService {

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("/clients")
	@Produces(MediaType.TEXT_XML)
	public Response listar() throws SQLException {
		Connection con = Conexao.getConnection();
		ClientDao dao = new ClientDaoImpl(con);
		List<Client> clients = dao.listar();
		return Response.ok(new GenericEntity< List< Client > >( clients ) { }).build();
	    
	}

	@POST
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_XML)
	public Response adicionar(JAXBElement<Client> dado) {

		Client client = dado.getValue();

		Connection con = Conexao.getConnection();
		ClientDao dao = new ClientDaoImpl(con);

		try {
			long id = dao.adicionar(client);
			String novoRecurso = uriInfo.getPath() + "/" + id;
			URI uri = new URI(novoRecurso);
			return Response.created(uri).build();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	
}
