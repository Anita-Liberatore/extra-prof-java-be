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
import entity.Repetition;
import utils.DbUtils;

public class RepetitionDao {

	protected EntityManager em;

	public RepetitionDao(EntityManager em) {
		this.em = em;
	}

	static final String DELETE = "delete from professor where id_professor= ";
	static final String FIND_ALL = "SELECT r from Repetitions r";

	public int addRepetition(Repetition repetition) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con=DbUtils.connectDB();
		
		try {
			PreparedStatement prep = con.prepareStatement ("INSERT INTO repetitions (ID_PROFESSOR, ID_COURSE, HOUR_REPETITION, DAY_REPETITIONS, STATUS, USER_REPETITION, ISDISPONIBILE) VALUES (?,?,?,?,?,?,?)");
            prep.setLong(1, repetition.getIdProfessor());
            prep.setLong (2, repetition.getIdCourse());
            prep.setString(3, repetition.getHour());
            prep.setString(4, repetition.getDay());
            prep.setString(5, repetition.getStatus());
            prep.setString(6, repetition.getUser());
            prep.setString(7, "NO");
            prep.executeUpdate ();
	        return 1;
		} catch(SQLException  e){
			System.out.println(e);

		}
		return -1;
	}
	
	public int updateRepetition(Long id, String status) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con=DbUtils.connectDB();
		
		try {
			PreparedStatement prep = con.prepareStatement ("UPDATE repetitions SET status = ? WHERE ID_REPETITIONS = ?");
			prep.setString(1, status);
			prep.setLong(2, id);
            prep.executeUpdate ();
	        return 1;
		} catch(SQLException  e){
			System.out.println(e);

		}
		return -1;
	}
	
	public int updateRepetitionDelete(Long id, String status) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con=DbUtils.connectDB();
		
		try {
			PreparedStatement prep = con.prepareStatement ("UPDATE repetitions SET status = ? WHERE ID_REPETITIONS = ?");
			prep.setString(1, status);
			prep.setLong(2, id);
            prep.executeUpdate ();
	        return 1;
		} catch(SQLException  e){
			System.out.println(e);

		}
		return -1;
	}

	public List<Repetition> findAll(String userParam) {
		List<Repetition>  listRepetitions = new ArrayList<>();
		Connection con=null;
		try {
			con=DbUtils.connectDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String query = "SELECT r.id_repetitions, p.name_professor, p.surname, c.name_course, r.hour_repetition, r.day_repetitions, r.status, r.user_repetition "
				+ "	 FROM repetitions r "
				+ "	 JOIN professor p on r.id_professor = p.id_professor "
				+ "	 JOIN course c on r.id_course = c.id_course where user_repetition = '"+userParam +"'";

		Statement stmt;
		try {

			stmt = con.createStatement();              

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Repetition repetition = new Repetition();
				Long id = rs.getLong(1);
				String nameProfessor = rs.getString(2);
				String surname = rs.getString(3);
				String nameCourse = rs.getString(4);
				String hour = rs.getString(5);
				String day = rs.getString(6);
				String status = rs.getString(7);
				String user = rs.getString(8);

				repetition.setId(id);
				repetition.setNameProfessor(nameProfessor);
				repetition.setSurnameProfessor(surname);
				repetition.setCourseName(nameCourse);
				repetition.setHour(hour);
				repetition.setDay(day);
				repetition.setStatus(status);
				repetition.setUser(user);

				listRepetitions.add(repetition);
			}

			stmt.close();
			con.close();

			return listRepetitions;

		} catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
		return new ArrayList<>();  
	}
	
	public List<Repetition> findAll() {
		List<Repetition>  listRepetitions = new ArrayList<>();
		Connection con=null;
		try {
			con=DbUtils.connectDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String query = "SELECT r.id_repetitions, p.name_professor, p.surname, c.name_course, r.hour_repetition, r.day_repetitions, r.status, r.user_repetition "
				+ "	 FROM repetitions r "
				+ "	 JOIN professor p on r.id_professor = p.id_professor "
				+ "	 JOIN course c on r.id_course = c.id_course ";

		Statement stmt;
		try {

			stmt = con.createStatement();              

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Repetition repetition = new Repetition();
				Long id = rs.getLong(1);
				String nameProfessor = rs.getString(2);
				String surname = rs.getString(3);
				String nameCourse = rs.getString(4);
				String hour = rs.getString(5);
				String day = rs.getString(6);
				String status = rs.getString(7);
				String user = rs.getString(8);

				repetition.setId(id);
				repetition.setNameProfessor(nameProfessor);
				repetition.setSurnameProfessor(surname);
				repetition.setCourseName(nameCourse);
				repetition.setHour(hour);
				repetition.setDay(day);
				repetition.setStatus(status);
				repetition.setUser(user);

				listRepetitions.add(repetition);
			}

			stmt.close();
			con.close();

			return listRepetitions;

		} catch(SQLException ex) {
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
		
		return new ArrayList<>(); 

	}


}
