package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import factories.ActivitatFactory;
import beans.Activitat;

public class ActivitatJNDIDAO implements IDAOActivitat{
	private String queryString;
	private final String ACTIVITATTABLENAME = "activitats";
	private Connection conexio;
	private PreparedStatement s = null;
	private Statement st = null;

	public ActivitatJNDIDAO(Connection c) {
	 conexio = c;
	}
	/**
	 * Implementacio del metode add de IDAOActivitat que afegeix una activitat
	 *  a la taula activitats de la base de dades bbdd.
	 */
	@Override
	public void add(Activitat activitat) {
		queryString = "INSERT INTO" + ACTIVITATTABLENAME + " NOM, DESCRIPCIO, DIA, HORA, ESPAI) VALUES(?,?,?,?,?)";
		
		try {
			s = conexio.prepareStatement(queryString);
			
			s.setString(0, activitat.getNom());
			s.setString(1,activitat.getDescripcio());
			s.setDate(2, new java.sql.Date(activitat.getData_realitzacio().getTime()
					.getTime()));
			s.setTimestamp(3, new java.sql.Timestamp(activitat.getHora().getTime()));
			s.setString(4, activitat.getEspai());
			
			s.executeUpdate();
			
			s.close();
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Implementacio del metode update de IDAOActivitats que modifica una activitats 
	 * de la taula activitat de la base de dades bbdd.
	 */
	@Override
	public void update(Activitat activitat) {
		queryString = "UPDATE" + ACTIVITATTABLENAME + "SET NOM = ?, DESCRIPCIO = ?, DIA= ?, HORA = ? ,ESPAI = ?,WHERE ID_ACTIVITAT = ?";
		
		try {
			s = conexio.prepareStatement(queryString);
			
			s.setString(0, activitat.getNom());
			s.setString(1, activitat.getDescripcio());
			s.setDate(2, new java.sql.Date(activitat.getData_realitzacio().getTime().getTime()));
			s.setTimestamp(3, new java.sql.Timestamp(activitat.getHora().getTime()));
			s.setString(4, activitat.getEspai());
			
			s.executeUpdate();
			
			s.close();
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Implementacio del metode delete de IDAOActivitat que elimina una activitat de la taula 
	 * activitats de la base de dades bbdd.
	 */
	@Override
	public void delete(Activitat activitat) {
		/*queryString = "DELETE FROM activitats WHERE ID_ACTIVITAT = ?";

		try {
			s = conexio.prepareStatement(queryString);
			s.setInt(1, activitat.geti);

			s.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		
	}
	/**
	 * Implementacio del metode getAll de IDAOActivitats que retorna un array d'activitats on hi han totes 
	 * les activitats de la taula activitats de la base de dades bbdd.
	 */
	@Override
	public Activitat[] getAll() {
		Activitat[] llistaActivitats = null;
		int i = 0;
		queryString = "SELECT * FROM" + ACTIVITATTABLENAME;
		
		try {
			ResultSet rs = (ResultSet) st.executeQuery(queryString);
			while(rs.next()){
				ActivitatFactory af = null;
				
				Calendar cal = new GregorianCalendar();
				cal.setTime(rs.getDate(3));
				
				llistaActivitats[i] = af.creaActivitat(rs.getString(1), rs.getString(2), cal, rs.getTimestamp(4),rs.getString(5),rs.getBoolean(6));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return llistaActivitats;
	}
	/**
	 * Implementacio del metode getAllNoSenior que retorna un array d'activitats que no siguin senior de la taula activitats
	 * de la base de dades activitats.
	 */
	@Override
	public Activitat[] getAllNoSenior() {
		Activitat[] llistaActivitats = null;
		int i = 0;
		queryString = "SELECT * FROM" + ACTIVITATTABLENAME + " WHERE SENIOR = 0";
		
		try {
			ResultSet rs = (ResultSet) st.executeQuery(queryString);
			while(rs.next()){
				ActivitatFactory af = null;
				
				Calendar cal = new GregorianCalendar();
				cal.setTime(rs.getDate(3));
				
				llistaActivitats[i] = af.creaActivitat(rs.getString(1), rs.getString(2), cal, rs.getTimestamp(4),rs.getString(5),rs.getBoolean(6));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return llistaActivitats;
	}
}
