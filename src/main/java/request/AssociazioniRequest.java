package request;

public class AssociazioniRequest {

	private Long idCourse;
	private Long idProfessor;
	/**
	 * @return the idCourse
	 */
	public Long getIdCourse() {
		return idCourse;
	}
	/**
	 * @param idCourse the idCourse to set
	 */
	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}
	/**
	 * @return the idProfessor
	 */
	public Long getIdProfessor() {
		return idProfessor;
	}
	/**
	 * @param idProfessor the idProfessor to set
	 */
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	@Override
	public String toString() {
		return "AssociazioniRequest [idCourse=" + idCourse + ", idProfessor=" + idProfessor + "]";
	}
	
	
}
