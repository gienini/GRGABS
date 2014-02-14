package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladors.CActivitats;
import controladors.CLogin;
import controladors.Controller;

public class Activitats extends HttpServlet {
	private String context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext().getRealPath("/staticactivitats");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * Inicialitzacio de variables
		 * 
		 */
		String pagina = "";
		Controller controlador = null;
		PrintWriter pw = resp.getWriter();

		try{
			if(req.getSession().getAttribute("login").equals("nologin")){
				controlador = new CActivitats();
				pagina = controlador.getPagina(context);
				pw.print(pagina);
			}
		} catch (Exception e) {
			controlador = new CLogin();
			pagina = controlador.getPagina(context);
		}
		pw.print(pagina);
			

	}

}
