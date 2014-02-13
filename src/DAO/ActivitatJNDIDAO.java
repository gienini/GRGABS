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
	private Connection conexio;
	private PreparedStatement s = null;
	private Statement st = null;

	public ActivitatJNDIDAO(Connection c) {
	 conexio = c;
	}

	@Override
	public void add(Activitat activitat) {
		queryString = "INSERT INTO activitats( NOM, DESCRIPCIO, DIA, HORA, ESPAI) VALUES(?,?,?,?,?)";
		
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

	@Override
	public void update(Activitat activitat) {
		queryString = "UPDATE activitats SET NOM = ?, DESCRIPCIO = ?, DIA= ?, HORA = ? ,ESPAI = ?,WHERE ID_ACTIVITAT = ?";
		
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

	@Override
	public Activitat[] getAll() {
		Activitat[] llistaActivitats = null;
		int i = 0;
		queryString = "SELECT * FROM activitats";
		
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

	@Override
	public Activitat[] getAllNoSenior() {
		Activitat[] llistaActivitats = null;
		int i = 0;
		queryString = "SELECT * FROM activitats WHERE SENIOR = 0";
		
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
