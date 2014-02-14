package controladors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.ActivitatJNDIDAO;
import DAO.SociJNDIDAO;
import beans.Activitat;
import beans.Soci;
import factories.SociFactory;

public class CActivitats implements Controller{

	Connection con;
	@Override
	public String getPagina(String s) {
		//Passem el context per paràmetre, d'aquesta manera obtenim el path del contingut estàtic
		//Passem el context per paràmetre, d'aquesta manera obtenim el path del contingut estàtic
		/*** Initialize connection ***/
		Context init = null;
		try {
			init = new InitialContext();
		} catch (NamingException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		Context env = null;
		try {
			env = (Context) init.lookup("java:comp/env");
		} catch (NamingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		DataSource ds = null;
		try {
			ds = (DataSource) env.lookup("jdbc/bbdd");
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String page = "";
		try {
			/*** Header ***/
			page = HTMLReader.getFile(new FileInputStream(s
					+ "/header.html"));			
			/*** Soci Personal Activities ***/
			page = page+getSociActivitats(s);
			/*** Footer ***/
			page = page+HTMLReader.getFile(new FileInputStream(s
					+ "/profile.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return page;

	}

	private String getSociActivitats(String s) {
		String content = "";
		String line = "";
		String activity = "";
		/***Get the soci **/
		ActivitatJNDIDAO activitatjndi = new ActivitatJNDIDAO(con);
		Activitat[] activitats = activitatjndi.getAll();
		try {
			BufferedReader read = new BufferedReader(new FileReader(s + "/bodyLoginOn.html"));
			try {
				line = read.readLine();
				while(line != null){
					//We need to find if the line contains a value
					if(line.contains("%activitat:info_activitat%")){
						for(Activitat activitat : activitats)
						{						
							activity = "<div class='col-md-12 margintop marginbot'><h3>Activitat: "+activitat.getNom()+" </h3>"
									+ "<p>"+activitat.getDescripcio()+" </p>"
											+ "<button type='button' class='btn btn-primary'>Més info</button>"
											+ "<button type='button' class='btn btn-success'>Apunta'm-hi</button>"
											+ "<button type='button' class='btn btn-danger'>Cancela activitat"
											+ "</button></div>";
							content = content + line.replaceAll("%activitat:info_activitat%", activity);
						}
						//line = line.replaceAll("%personal:variable%", "Hello World");
					} 
					content = content + line;
					line = read.readLine();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return content;
	}

	@Override
	public String getPagina(String s, String dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
