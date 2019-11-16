package br.com.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.restful.model.Cliente;

@Path("/cliente")
public class ClienteResource {
	
	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	public String listarTodos() {
		Cliente cliente = new Cliente();
		cliente.setEndereco("dsadasda");
		return cliente.getEndereco();
	}

}
