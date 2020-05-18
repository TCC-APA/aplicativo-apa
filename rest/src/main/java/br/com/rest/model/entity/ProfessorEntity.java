package br.com.rest.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProfessorEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	

}
