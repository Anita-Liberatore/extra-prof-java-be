package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Course;

public class CourseDao {

	protected EntityManager em;

	public CourseDao(EntityManager em) {
		this.em = em;
	}
	
	static final String DELETE = "delete from course where id_course= ";
	static final String FIND_ALL = "SELECT c FROM Course c";
	
	public List<Course> findAll() {
		Query query = em.createQuery(FIND_ALL);
		return query.getResultList();

	}
}
