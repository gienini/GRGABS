package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import factories.ActivitatFactory;
import beans.Activitat;

public class ActivitatJNDIDAO implements IDAOActivitat{
	private String queryString;
	private final String ACTIVITATTABLENAME = "activitats";
	private Connection conexio;
	private PreparedStatement s = null;
	

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
	public ArrayList<Activitat> getAll() {
		int i = 0;
		queryString = "SELECT * FROM " + ACTIVITATTABLENAME;
		ArrayList<Activitat> llistaActivitats = null;
		try {
			s = conexio.prepareStatement(queryString);
			ResultSet rs = s.executeQuery();
			llistaActivitats = new ArrayList<Activitat>();
			ActivitatFactory af = af = new ActivitatFactory();
			while(rs.next()){
				
				
				Calendar cal = new GregorianCalendar();
				cal.setTime(rs.getDate(4));
				
				llistaActivitats.add(af.creaActivitat(rs.getString(2), rs.getString(3), cal, rs.getTimestamp(5),rs.getString(6),rs.getBoolean(7)));
		
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
	public ArrayList<Activitat> getAllNoSenior() {
		int i = 0;
		queryString = "SELECT * FROM " + ACTIVITATTABLENAME + " WHERE SENIOR = FALSE";
		ArrayList<Activitat> llistaActivitats = null;
		try {
			s = conexio.prepareStatement(queryString);
			ResultSet rs = s.executeQuery();
			llistaActivitats = new ArrayList<Activitat>();
			ActivitatFactory af = af = new ActivitatFactory();
			while(rs.next()){
				
				
				Calendar cal = new GregorianCalendar();
				cal.setTime(rs.getDate(4));
				
				llistaActivitats.add(af.creaActivitat(rs.getString(2), rs.getString(3), cal, rs.getTimestamp(5),rs.getString(6),rs.getBoolean(7)));
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return llistaActivitats;
	}
}
