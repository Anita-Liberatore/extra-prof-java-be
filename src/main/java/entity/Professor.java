package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor implements Serializable {
	
	private static final long serialVersionUID = -892731674945086346L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_professor")
	private Long id;

	@Column(name = "name_professor")
	private String name;
	

	@Column(name = "surname")
	private String surname;
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}


	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}


	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}



	
}
