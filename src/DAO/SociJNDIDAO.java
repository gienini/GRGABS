package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import factories.SociFactory;
import beans.Activitat;
import beans.Soci;

public class SociJNDIDAO implements IDAOSoci {
    private final String SOCISTABLENAME="socis";
	private String queryString;
	private Connection conexio;
	private PreparedStatement s = null;
	public SociFactory socifactory = new SociFactory();
	public SociJNDIDAO(Connection c) {
	 conexio = c;
	}

	@Override
	public void add(Soci soci) {

		queryString = "INSERT INTO " + SOCISTABLENAME + "(DNI, NOM, COGNOM1, COGNOM2,NICKNAME,PASW,ADRECA,DATA_NAIX,DATA_ALTA,FOTO) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			s = conexio.prepareStatement(queryString);

			s.setString(1, soci.getDni());
			s.setString(2, soci.getNom());
			s.setString(3, soci.getCog1());
			s.setString(4, soci.getCog2());
			//nickname es buit
			s.setString(5, "");
			s.setString(6, soci.getPasw());
			s.setString(7, soci.getAdreca());
			s.setDate(8, new java.sql.Date(soci.getData_naixement().getTime()
					.getTime()));
			s.setDate(9, new java.sql.Date(soci.getData_alta().getTime()
					.getTime()));
			s.setString(10, soci.getFoto());
			

			s.executeUpdate();

			s.close();
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Soci soci) {

		queryString = "UPDATE " + SOCISTABLENAME + " SET NOM = ?, COGNOM1 = ?, COGNOM2 = ?, PASW = ? ,ADRECA = ?,DATA_NAIX = ?,FOTO=? WHERE DNI = ?";

		try {

			s = conexio.prepareStatement(queryString);

			s.setString(1, soci.getNom());
			s.setString(2, soci.getCog1());
			s.setString(3, soci.getCog2());
			s.setString(4, soci.getPasw());
			s.setString(5, soci.getAdreca());
			s.setDate(6, new java.sql.Date(soci.getData_naixement().getTime()
					.getTime()));
			s.setString(7, soci.getFoto());
			s.setString(8, soci.getDni());

			s.executeUpdate();

			s.close();
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Soci soci) {
		queryString = "DELETE FROM  " + SOCISTABLENAME + " WHERE dni = ?";

		try {
			s = conexio.prepareStatement(queryString);
			s.setString(1, soci.getDni());

			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Soci getSoci(String dni) {
		Soci soc = null;
		queryString = "SELECT nom,cognom1,cognom2,pasw,adreca,data_naix,data_alta,foto FROM " + SOCISTABLENAME + " socis WHERE dni = ?";
		try {
			s = conexio.prepareStatement(queryString);
			s.setString(1, dni);
			ResultSet rs = s.executeQuery();
			rs.next();
			GregorianCalendar data_naix = new GregorianCalendar();
			data_naix.setTime(rs.getDate(6));
			GregorianCalendar data_alta = new GregorianCalendar();
			data_alta.setTime(rs.getDate(7));
			soc = socifactory.crearSoci(
					dni,
					rs.getString(1),//nom
					rs.getString(2),//cognom
					rs.getString(3),//cog2
					rs.getString(5),//adreca
					data_naix,//naix
					data_alta,//data_alta
					rs.getString(8),
					rs.getString(4));
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return soc;
		
	}

	@Override
	public boolean isLogin(String Dni, String password) {
		// TODO Auto-generated method stub
		queryString = "SELECT COUNT(*) FROM SOCIS WHERE DNI=? AND PASW=?;";
		//
		try {
			s = conexio.prepareStatement(queryString);

			s.setString(1, Dni);
			s.setString(2, password);
			ResultSet rs;
			rs = (ResultSet) s.executeQuery();
			rs.next();
			if (rs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void joinActivitat(Soci soci, Activitat act) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leaveActivitat(Soci soci, Activitat act) {
		// TODO Auto-generated method stub

	}

	
	@Override
	/**
	 *COMPROVAR SI ESTÀ EN UNA ACTIVITAT AMB EL MATEIX NOM, OJU, QUE LA BASE DE DADES TÉ
	 *UN CAMP  
	 */
	public boolean isOnActivitat(Soci soci, Activitat act) {
		// TODO Auto-generated method stub
		queryString ="SELECT COUNT(*) FROM SOCIS_ACTIVITATS WHERE SOCIS_ACTIVITATS.DNI='" + soci.getDni() + "' AND ID_ACTIVITAT=(SELECT ACTIVITATS.ID_ACTIVITAT FROM ACTIVITATS WHERE ACTIVITATS.NOM='" + act.getNom() + "');";
		try {
			s = conexio.prepareStatement(queryString);
			ResultSet rs;
			rs = (ResultSet) s.executeQuery();
			rs.next();
			if (rs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Activitat[] getActivitats(Soci soci) {
		// TODO Auto-generated method stub
		return null;
	}

}
