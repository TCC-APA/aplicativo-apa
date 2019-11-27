package br.com.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.rest.model.entity.AlunoEntity;
import br.com.rest.services.AlunoServices;

@Path("/login")
public class LoginApi {	
		
	@POST
	@Path("inserir")
	@Produces("application/octet-stream")
	public String inserirAluno(AlunoEntity aluno) {
		return AlunoServices.incluirAluno(aluno).toString();
	}
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public AlunoEntity realizarLoginPorMatriculaSenha(@QueryParam("matricula") String matricula, @QueryParam("senha") String senha) {
		return AlunoServices.consultarAlunoPorMatriculaSenha(matricula, senha);
	}

}
