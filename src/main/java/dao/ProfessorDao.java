package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Professor;

public class ProfessorDao {
	
	protected EntityManager em;

	public ProfessorDao(EntityManager em) {
		this.em = em;
	}

	static final String DB_URL = "jdbc:postgresql://localhost:5432/TRAINING?useSSL=false&serverTimeZone=UTC";
	static final String USER = "postgres";
	static final String PASS = "root";
	static final String DRIVER = "org.postgresql.Driver";
	
	static final String DELETE = "delete from professor where id_professor= ";
	static final String FIND_ALL = "SELECT p FROM Professor p";



	public static Connection connectDB() throws ClassNotFoundException {
		try {
			Connection con = null;

			con = DriverManager.getConnection(
					DB_URL,
					USER, PASS);

			return con;
		} catch (SQLException e) {

			System.out.println(e);
		}
		return null;
	}

	public int deleteProfessor(Long id) throws ClassNotFoundException {
		Connection con=null;
		PreparedStatement p=null;
		con=ProfessorDao.connectDB();

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

	public List<Professor> findAll() {
		Query query = em.createQuery(FIND_ALL);
		return query.getResultList();

	}

}
