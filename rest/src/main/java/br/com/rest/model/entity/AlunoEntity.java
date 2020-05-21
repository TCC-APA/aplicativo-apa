package br.com.rest.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class AlunoEntity{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String cpf;

	@Column
	private String nome;

	@Column
	private String email;
	
	@Column
	private int idade;
	
	@Column
	private String genero;

	@Column
	private String senha;
	
	@Column
	private String matricula;

	@Column
	private String turma;

	@Column
	private Integer perfilAtivo;
	
	@Column
	private Integer perfilReflexivo;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public void setTurma(String turma) {
		this.turma = turma;
	}

	@Column
	private Integer perfilPragmatico;
	
	@Column
	private Integer perfilTeorico;
	
	public String getTurma() {
		return turma;
	}
	

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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


}
