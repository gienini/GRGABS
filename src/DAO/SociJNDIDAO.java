package DAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.naming.*;
import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StatementImpl;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import beans.Activitat;
import beans.Soci;
import factories.GetConnection;

public class SociJNDIDAO implements IDAOSoci{
	String queryString;
	
	GetConnection gcConnection = new GetConnection();
	Connection c = (Connection) gcConnection.getJNDIConnection();
	PreparedStatement s = null;

	@Override
	public void add(Soci soci) {

		queryString = "INSERT INTO soci(DNI, NOM, COGNOM1, COGNOM2,PASW,ADRECA,DATA_NAIX,DATA_ALTA) VALUES(?,?,?,?,?,?,?,?)";

		try {
			s = new PreparedStatement((MySQLConnection) c,queryString);

			s.setString(1, soci.getDni());
			s.setString(2, soci.getNom());
			s.setString(3, soci.getCog1());
			s.setString(4, soci.getCog2());
			s.setString(5, soci.getPasw());
			s.setString(6, soci.getAdreca());
			s.setDate(7, new java.sql.Date(soci.getData_naixement().getTime().getTime()));
			s.setDate(8, new java.sql.Date(soci.getData_alta().getTime().getTime()));

			s.execute(queryString);

			s.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Soci soci) {
		
		queryString = "UPDATE soci SET NOM = ?, COGNOM1 = ?, COGNOM2 = ?, PASW = ? ,ADRECA = ?,DATA_NAIX = ? WHERE DNI = ?";

		try {

			s = new PreparedStatement((MySQLConnection) c,queryString);

			s.setString(1, soci.getNom());
			s.setString(2, soci.getCog1());
			s.setString(3, soci.getCog2());
			s.setString(4, soci.getPasw());
			s.setString(5, soci.getAdreca());
			//s.setDate(6, soci.getData_naixement());
			s.setString(7,soci.getDni());

			s.execute();

			s.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void delete(Soci soci) {
		queryString = "DELETE FROM soci WHERE dni = ?";
		
		try {
			s = new PreparedStatement((MySQLConnection) c,queryString);
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
		queryString = "SELECT COUNT (*) FROM SOCIS WHERE DNI=? AND PASW=?";
		
		try {
			s = new PreparedStatement((MySQLConnection) c,queryString);
			
			s.setString(1, Dni);
			s.setString(2, password);
			ResultSet rs;
			rs = (ResultSet) s.executeQuery();
			rs.next();
			if(rs.getInt(0) == 1){
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
