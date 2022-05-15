package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entity.Associazione;
import utils.DbUtils;

public class AssociazioneDao {


	protected EntityManager em;

	public AssociazioneDao(EntityManager em) {
		this.em = em;
	}
	
	static final String DELETE = "delete from associazione_corso_docente where id_associazione= ";
	static final String FIND_ALL = "SELECT a FROM Associazione a";
	
	public int deleteAssociazione(Long id) throws ClassNotFoundException {
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
	
	public List<Associazione> findAll() {
		List<Associazione>  listAssociazione = new ArrayList<>();
		Connection con=null;
		try {
			con=DbUtils.connectDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	    String query = "SELECT a.id_associazione, a.id_corso, a.id_docente, p.name_professor, p.surname, c.name_course " +
	    		" FROM associazione_corso_docente a " +
	    		" JOIN professor p on a.id_docente = p.id_professor "+ 
	    		" JOIN course c on a.id_corso = c.id_course";
	    
	    Statement stmt;
	    try {
	  
	      stmt = con.createStatement();              
	  
	      ResultSet rs = stmt.executeQuery(query);
	      
	      while (rs.next()) {
	    	Associazione associazione = new Associazione();
	        Long id = rs.getLong(1);
	        Long idCorso = rs.getLong(2);
	        Long idDocente = rs.getLong(3);
	        String nameProfessor = rs.getString(4);
	        String surname = rs.getString(5);
	        String nameCourse = rs.getString(6);
	        
	        associazione.setId(id);
	        associazione.setIdCorso(idCorso);
	        associazione.setIdDocente(idDocente);
	        associazione.setNameProfessor(nameProfessor);
	        associazione.setNameCourse(nameCourse);
	        associazione.setSurnameProfessor(surname);
	        
	        listAssociazione.add(associazione);
	      }

	      stmt.close();
	      con.close();
	      
	      return listAssociazione;

	    } catch(SQLException ex) {
	      System.err.print("SQLException: ");
	      System.err.println(ex.getMessage());
	    }
		return new ArrayList<>();  
	  }
}
