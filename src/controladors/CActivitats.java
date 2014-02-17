package controladors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.ActivitatJNDIDAO;
import DAO.SociJNDIDAO;
import beans.Activitat;
import beans.Senior;
import beans.Soci;
import factories.SociFactory;

public class CActivitats implements Controller {

	Connection con;

	public String getPagina(String s, String dni) {
		// Passem el context per paràmetre, d'aquesta manera obtenim el path del
		// contingut estàtic
		// Passem el context per paràmetre, d'aquesta manera obtenim el path del
		// contingut estàtic
		/*** Initialize connection ***/
		Context init = null;
		DataSource ds = null;
		Context env = null;
		try {
			init = new InitialContext();

			env = (Context) init.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/bbdd");
			con = ds.getConnection();
		} catch (NamingException e4) {
			e4.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String page = "";
		try {
			/*** Header ***/
			page = HTMLReader.getFile(new FileInputStream(s + "/header.html"));
			/*** Soci Personal Activities ***/
			page = page + getSociActivitats(dni);
			/*** Footer ***/
			page = page
					+ HTMLReader
							.getFile(new FileInputStream(s + "/footer.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return page;

	}

	private String getSociActivitats(String dni) {
		String content = "";
		String activity = "";
		/*** Get the soci **/
		SociJNDIDAO socijndi = new SociJNDIDAO(con);
		SociFactory socif = new SociFactory();
		Soci soci = socif.crearSoci();
		soci = socijndi.getSoci(dni);
		/***Get the activities **/
		ActivitatJNDIDAO activitatjndi = new ActivitatJNDIDAO(con);
		ArrayList<Activitat> activitats = new ArrayList<Activitat>();
		if(soci instanceof Senior){
			activitats = activitatjndi.getAll();
		} else {
			activitats = activitatjndi.getAllNoSenior();
		}
		content="<div class='container marginbot'>";
		for (Activitat activitat : activitats) {
			System.out.println(activitat.getNom());
			activity = "<div class='col-md-4 margintop marginbot'><h3>Activitat: "
					+ activitat.getNom()
					+ " </h3>"
					+ "<p>"
					+ activitat.getDescripcio()
					+ " </p>"
					+ "<button type='button' class='btn btn-primary'>Més info</button>"
					+ "<button type='button' class='btn btn-success'>Apunta'm-hi</button>"
					+ "<button type='button' class='btn btn-danger'>Cancela activitat"
					+ "</button></div>";
			content = content + activity;
		}
		content = content+"</div>";

		return content;
	}

	@Override
	public String getPagina(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
