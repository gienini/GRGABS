package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Activitat;
import beans.Soci;

public class SociJNDIDAO implements IDAOSoci {
	private String queryString;

	private Connection conexio;
	private PreparedStatement s = null;

	public SociJNDIDAO(Connection c) {
	 conexio = c;
	}

	@Override
	public void add(Soci soci) {

		queryString = "INSERT INTO soci(DNI, NOM, COGNOM1, COGNOM2,PASW,ADRECA,DATA_NAIX,DATA_ALTA) VALUES(?,?,?,?,?,?,?,?)";

		try {
			s = conexio.prepareStatement(queryString);

			s.setString(1, soci.getDni());
			s.setString(2, soci.getNom());
			s.setString(3, soci.getCog1());
			s.setString(4, soci.getCog2());
			s.setString(5, soci.getPasw());
			s.setString(6, soci.getAdreca());
			s.setDate(7, new java.sql.Date(soci.getData_naixement().getTime()
					.getTime()));
			s.setDate(8, new java.sql.Date(soci.getData_alta().getTime()
					.getTime()));

			s.execute(queryString);

			s.close();
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Soci soci) {

		queryString = "UPDATE soci SET NOM = ?, COGNOM1 = ?, COGNOM2 = ?, PASW = ? ,ADRECA = ?,DATA_NAIX = ? WHERE DNI = ?";

		try {

			s = conexio.prepareStatement(queryString);

			s.setString(1, soci.getNom());
			s.setString(2, soci.getCog1());
			s.setString(3, soci.getCog2());
			s.setString(4, soci.getPasw());
			s.setString(5, soci.getAdreca());
			// s.setDate(6, soci.getData_naixement());
			s.setString(7, soci.getDni());

			s.execute();

			s.close();
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Soci soci) {
		queryString = "DELETE FROM soci WHERE dni = ?";

		try {
			s = conexio.prepareStatement(queryString);
			s.setString(1, soci.getDni());

			s.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void getSoci(String dni) {

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
	public boolean isOnActivitat(Soci soci, Activitat act) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Activitat[] getActivitats(Soci soci) {
		// TODO Auto-generated method stub
		return null;
	}

}
