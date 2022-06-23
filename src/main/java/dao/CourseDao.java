package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Course;
import entity.Professor;
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
	
	public int addCourse(Course couse) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con=DbUtils.connectDB();
		
		try {
			PreparedStatement prep = con.prepareStatement ("INSERT INTO course (NAME_COURSE) VALUES (?)");
            prep.setString (1, couse.getCourseName());
            prep.executeUpdate ();
	        return 1;
		} catch(SQLException  e){
			System.out.println(e);

		}
		
		return -1;
	}
	
	public Course courseByName(String name) throws ClassNotFoundException {
		Course  course = new Course();
		Connection con=null;
		try {
			con=DbUtils.connectDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		Statement stmt;
		try {

			stmt = con.createStatement();              

			ResultSet rs = stmt.executeQuery("SELECT name_course from course where name_course = '"+name +"'");

			while (rs.next()) {
				String nameCourse = rs.getString(1);
				course.setCourseName(nameCourse);
			}

			stmt.close();
			con.close();

			return course;

		} catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
		return new Course();  
	}
}
