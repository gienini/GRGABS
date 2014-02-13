package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAO.SociJNDIDAO;
import controladors.CLogin;
import controladors.CRegistre;
import controladors.Controller;

/**
 * Clase per a la gestio de les vistes de login i registre
 * 
 * @author Fran Gienini
 * 
 */
public class Login extends HttpServlet {

	private String context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext().getRealPath("/staticlogin");
	}

	@Override
	/**
	 * Gestiona les peticions get per a la part de login
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * Inicialitzacio de variables
		 * 
		 */

		String pagina = "";
		Controller controlador = null;
		String params = "";
		PrintWriter pw = resp.getWriter();
		/**
		 * Comparacio: formulari de registre o de login
		 * 
		 * CATCH per mirar el contingut de get. s'incloura el parametre
		 * "registre" al get per accedir a la pagina de registre totes les
		 * altres opcions portaran a la pagina de login
		 * 
		 */

		try {
			if (req.getParameterNames().nextElement().equals("registre")) {
				controlador = new CRegistre();
				pagina = controlador.getPagina(context);

			} else {
				controlador = new CLogin();
				pagina = controlador.getPagina(context);
			}
		} catch (Exception e) {
			controlador = new CLogin();
			pagina = controlador.getPagina(context);
		}

		pw.write(pagina);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * Si el valor de "user" al formulari post es diferent de null
		 * significara que aquesta clau conte el DNI del usuari
		 * 
		 */
		try {
			if (!req.getParameter("user").equals(null)) {
				/**
				 * Pasem el context per poder accedir a la BD mitjançant JNDI
				 * 
				 */
				Context init = new InitialContext();
				Context env = (Context) init.lookup("java:comp/env");
				DataSource ds = (DataSource) env.lookup("jdbc/bbdd");
				Connection con = ds.getConnection(); 
				SociJNDIDAO login = new SociJNDIDAO(con);
				if (login.isLogin(req.getParameter("user"),
						req.getParameter("pass"))) {
					req.getSession().setAttribute("login", "");
					resp.sendRedirect("FUNCIONA");
				} else
					resp.sendRedirect("NOFUNCIONA");
				String usuari = "";
				
				con.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
