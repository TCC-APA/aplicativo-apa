package br.com.rest.model.entity;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class QuestionarioEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ElementCollection
	private List<String> questoes;
	
	@Column
	@ManyToMany
	private TurmaEntity turma;
	
	@Column
	@ManyToOne
	private ProfessorEntity professor;
	
	//ver como fazer o mapeamento dos negocio
	
}
