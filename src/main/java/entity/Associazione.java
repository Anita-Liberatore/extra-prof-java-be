package entity;

public class Associazione {
	
	private Long id;

	private Long idCorso;
	
	private Long idDocente;
	
	private String nameProfessor;
	
	private String nameCourse;
	
	private String surnameProfessor;
	
	

	/**
	 * @return the surnameProfessor
	 */
	public String getSurnameProfessor() {
		return surnameProfessor;
	}

	/**
	 * @param surnameProfessor the surnameProfessor to set
	 */
	public void setSurnameProfessor(String surnameProfessor) {
		this.surnameProfessor = surnameProfessor;
	}

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
	 * @return the idCorso
	 */
	public Long getIdCorso() {
		return idCorso;
	}

	/**
	 * @param idCorso the idCorso to set
	 */
	public void setIdCorso(Long idCorso) {
		this.idCorso = idCorso;
	}

	/**
	 * @return the idDocente
	 */
	public Long getIdDocente() {
		return idDocente;
	}

	/**
	 * @param idDocente the idDocente to set
	 */
	public void setIdDocente(Long idDocente) {
		this.idDocente = idDocente;
	}

	/**
	 * @return the nameProfessor
	 */
	public String getNameProfessor() {
		return nameProfessor;
	}

	/**
	 * @param nameProfessor the nameProfessor to set
	 */
	public void setNameProfessor(String nameProfessor) {
		this.nameProfessor = nameProfessor;
	}

	/**
	 * @return the nameCourse
	 */
	public String getNameCourse() {
		return nameCourse;
	}

	/**
	 * @param nameCourse the nameCourse to set
	 */
	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	@Override
	public String toString() {
		return "Associazione [id=" + id + ", idCorso=" + idCorso + ", idDocente=" + idDocente + ", nameProfessor="
				+ nameProfessor + ", nameCourse=" + nameCourse + ", surnameProfessor=" + surnameProfessor + "]";
	}
	
}
