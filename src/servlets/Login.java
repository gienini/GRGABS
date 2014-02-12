package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter pw = resp.getWriter();
		/**
		 * Decisio: formulari de registre o de login
		 */
		if (req.getParameterNames().nextElement().equals("registre")) {
			controlador = new CRegistre();
			pagina = controlador.getPagina(null);

		} else {
			controlador = new CLogin();
			pagina = controlador.getPagina(null);
		}
		pw.write(pagina);
	}

}
