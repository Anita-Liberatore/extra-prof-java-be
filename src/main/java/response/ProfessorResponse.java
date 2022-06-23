package response;

import java.util.ArrayList;
import java.util.List;

public class ProfessorResponse {


	private Long id;

	private String name;
	
	private String surname;
	
	private List<String> hours = new ArrayList<>();
	
	private String courseName;

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

	/**
	 * @return the hours
	 */
	public List<String> getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(List<String> hours) {
		this.hours = hours;
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

	@Override
	public String toString() {
		return "ProfessorResponse [id=" + id + ", name=" + name + ", surname=" + surname + ", hours=" + hours
				+ ", courseName=" + courseName + "]";
	}
	
	
}
