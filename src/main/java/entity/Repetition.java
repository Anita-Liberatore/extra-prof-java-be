package entity;

public class Repetition {
	

	private Long id;
	private Long idProfessor;
	private Long idCourse;
	private String hour;
	private String day;
	private String status;
	private String user;
	private String nameProfessor;
	private String surnameProfessor;
	private String courseName;
	private String isdisponibile;
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
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
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
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the isdisponibile
	 */
	public String getIsdisponibile() {
		return isdisponibile;
	}
	/**
	 * @param isdisponibile the isdisponibile to set
	 */
	public void setIsdisponibile(String isdisponibile) {
		this.isdisponibile = isdisponibile;
	}
	@Override
	public String toString() {
		return "Repetition [id=" + id + ", idProfessor=" + idProfessor + ", idCourse=" + idCourse + ", hour=" + hour
				+ ", day=" + day + ", status=" + status + ", user=" + user + ", nameProfessor=" + nameProfessor
				+ ", surnameProfessor=" + surnameProfessor + ", courseName=" + courseName + ", isdisponibile="
				+ isdisponibile + "]";
	}

	

	

}
