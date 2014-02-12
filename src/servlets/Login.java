package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		context = config.getServletContext().getRealPath("/static");
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
}
