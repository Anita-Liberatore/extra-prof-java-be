package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Course;
import utils.DbUtils;

public class CourseDao {

	protected EntityManager em;

	public CourseDao(EntityManager em) {
		this.em = em;
	}
	
	static final String DELETE = "delete from course where id_course= ";
	static final String FIND_ALL = "SELECT c FROM Course c";
	
	@SuppressWarnings("unchecked")
	public List<Course> findAll() {
		Query query = em.createQuery(FIND_ALL);
		return query.getResultList();

	}
	
	public int deleteCourse(Long id) throws ClassNotFoundException {
		Connection con=null;
		PreparedStatement p=null;
		con=DbUtils.connectDB();

		try{
			String sql=DELETE+id;
			p = con.prepareStatement(sql);
			p.execute();
			return 1;
		}catch(SQLException  e){
			System.out.println(e);

		}
		return -1;
	}
}
