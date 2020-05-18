package br.com.rest.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class AlunoEntity extends Usuario {
	
	@Column
	private String matricula;

	@Column
	private String turma;

	@Column
	private Integer perfilAtivo;
	
	@Column
	private Integer perfilReflexivo;
	
	@Column
	private Integer perfilPragmatico;
	
	@Column
	private Integer perfilTeorico;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTurma() {
		return turma;
	}
	
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
	public Integer getPerfilAtivo() {
		return perfilAtivo;
	}
	
	public void setPerfilAtivo(Integer perfilAtivo) {
		this.perfilAtivo = perfilAtivo;
	}
	
	public Integer getPerfilReflexivo() {
		return perfilReflexivo;
	}
	
	public void setPerfilReflexivo(Integer perfilReflexivo) {
		this.perfilReflexivo = perfilReflexivo;
	}
	
	public Integer getPerfilPragmatico() {
		return perfilPragmatico;
	}
	
	public void setPerfilPragmatico(Integer perfilPragmatico) {
		this.perfilPragmatico = perfilPragmatico;
	}
	
	public Integer getPerfilTeorico() {
		return perfilTeorico;
	}
	
	public void setPerfilTeorico(Integer perfilTeorico) {
		this.perfilTeorico = perfilTeorico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + idade;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfilAtivo == null) ? 0 : perfilAtivo.hashCode());
		result = prime * result + ((perfilPragmatico == null) ? 0 : perfilPragmatico.hashCode());
		result = prime * result + ((perfilReflexivo == null) ? 0 : perfilReflexivo.hashCode());
		result = prime * result + ((perfilTeorico == null) ? 0 : perfilTeorico.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlunoEntity other = (AlunoEntity) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idade != other.idade)
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfilAtivo == null) {
			if (other.perfilAtivo != null)
				return false;
		} else if (!perfilAtivo.equals(other.perfilAtivo))
			return false;
		if (perfilPragmatico == null) {
			if (other.perfilPragmatico != null)
				return false;
		} else if (!perfilPragmatico.equals(other.perfilPragmatico))
			return false;
		if (perfilReflexivo == null) {
			if (other.perfilReflexivo != null)
				return false;
		} else if (!perfilReflexivo.equals(other.perfilReflexivo))
			return false;
		if (perfilTeorico == null) {
			if (other.perfilTeorico != null)
				return false;
		} else if (!perfilTeorico.equals(other.perfilTeorico))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlunoEntity [id=" + id + ", cpf=" + cpf + ", matricula=" + matricula + ", nome=" + nome + ", email="
				+ email + ", turma=" + turma + ", idade=" + idade + ", genero=" + genero + ", senha=" + senha
				+ ", perfilAtivo=" + perfilAtivo + ", perfilReflexivo=" + perfilReflexivo + ", perfilPragmatico="
				+ perfilPragmatico + ", perfilTeorico=" + perfilTeorico + "]";
	}
}
