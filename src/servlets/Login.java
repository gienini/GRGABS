package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.Soci;
import DAO.SociJNDIDAO;
import controladors.CLogin;
import controladors.CRegistre;
import controladors.Controller;
import factories.SociFactory;

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
		context = config.getServletContext().getRealPath("/");
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

	private Connection contextConnection() {
		Context init;
		try {
			init = new InitialContext();
			Context env = (Context) init.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/bbdd");
			Connection con = ds.getConnection();
			return con;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * Si el valor de "user" al formulari post es diferent de null
		 * significara que aquesta clau conte el DNI del usuari
		 * 
		 */
		req.getSession().setAttribute("login", "nologin");
		try {
			if (!req.getParameter("user").equals(null)) {
				/**
				 * Agafem la conexio JNDI del nostre context, ja que desde les
				 * altres classes el trobarem inaccesible
				 * 
				 */

				Connection con = contextConnection();
				/**
				 * La pasem al nostre DAO per comparar usuaris i contrasenya
				 * 
				 */
				SociJNDIDAO login = new SociJNDIDAO(con);
				if (login.isLogin(req.getParameter("user"),
						req.getParameter("pass"))) {
					/**
					 * S'inicialitza el valor de la sesio "login" amb el DNI de
					 * l'usuari
					 * 
					 */
					req.getSession().setAttribute("login",
							req.getParameter("user"));
					resp.sendRedirect("/GRGABS/activitats");
				} else {

					resp.sendRedirect("/GRGABS/login");
				}
				String usuari = "";

				con.close();

			} else if (!(req.getParameter("nom").equals(null)
					&& req.getParameter("cognom1").equals(null)
					&& req.getParameter("cognom2").equals(null)
					&& req.getParameter("dni").equals(null)
					&& req.getParameter("adreca").equals(null)
					&& req.getParameter("data-naix").equals(null) && req
					.getParameter("passR").equals(null))) {
				SociJNDIDAO registre = new SociJNDIDAO(contextConnection());

				Soci s = new Soci(req.getParameter("dni"),
						req.getParameter("nom"), req.getParameter("cognom1"),
						req.getParameter("cognom2"),
						req.getParameter("adreca"), new GregorianCalendar(2000,
								5, 5), new GregorianCalendar(2000, 5, 5), "");
				s.setPasw(req.getParameter("passR"));
				registre.add(s);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
