package response;

public class ProfessorNotDisponibilityResponse {

	private Long id;
	private String dayRepetition;
	private String hourRepetition;
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
	 * @return the dayRepetition
	 */
	public String getDayRepetition() {
		return dayRepetition;
	}
	/**
	 * @param dayRepetition the dayRepetition to set
	 */
	public void setDayRepetition(String dayRepetition) {
		this.dayRepetition = dayRepetition;
	}
	/**
	 * @return the hourRepetition
	 */
	public String getHourRepetition() {
		return hourRepetition;
	}
	/**
	 * @param hourRepetition the hourRepetition to set
	 */
	public void setHourRepetition(String hourRepetition) {
		this.hourRepetition = hourRepetition;
	}
	@Override
	public String toString() {
		return "ProfessorNotDisponibilityResponse [id=" + id + ", dayRepetition=" + dayRepetition + ", hourRepetition="
				+ hourRepetition + "]";
	}
}
