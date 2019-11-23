package restproj;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/teste")
public class R1 {
	
	@GET
	@Path("/testeurl")
	@Produces(MediaType.APPLICATION_JSON)
	public Objeto teste(@QueryParam("cpf") String cpf) {
		Objeto obj = new Objeto();
		obj.setCpf(cpf);
		return obj;
	}

}
