package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Professor;
import utils.DbUtils;

public class ProfessorDao {
	
	protected EntityManager em;

	public ProfessorDao(EntityManager em) {
		this.em = em;
	}
	
	static final String DELETE = "delete from professor where id_professor= ";
	static final String FIND_ALL = "SELECT p FROM Professor p";


	public int deleteProfessor(Long id) throws ClassNotFoundException {
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
	
	public int addProfessor(Professor professor) throws ClassNotFoundException, SQLException {
		Connection con = null;
		con=DbUtils.connectDB();
		
		try {
			PreparedStatement prep = con.prepareStatement ("INSERT INTO professor (NAME_PROFESSOR, SURNAME) VALUES (?,?)");
            prep.setString (1, professor.getName());
            prep.setString (2, professor.getSurname());
            prep.executeUpdate ();
	        return 1;
		} catch(SQLException  e){
			System.out.println(e);

		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public List<Professor> findAll() {
		Query query = em.createQuery(FIND_ALL);
		return query.getResultList();

	}

}
