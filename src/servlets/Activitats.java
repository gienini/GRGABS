package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladors.CActivitats;
import controladors.CLogin;
import controladors.CPersonal;
import controladors.Controller;

@WebServlet("/activitats")
public class Activitats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext().getRealPath("/staticactivitats");
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Inicialitzacio de variables
		 * 
		 */
		String pagina = "";
		Controller controlador = null;
		PrintWriter pw = response.getWriter();

		try{
			//if(req.getSession().getAttribute("login").equals("nologin")){
				controlador = new CActivitats();
				pagina = controlador.getPagina(context);
				//pw.print(pagina);
			//}
		} catch (Exception e) {
			controlador = new CLogin();
			pagina = controlador.getPagina(context);
		}
		pw.print(pagina);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

}
