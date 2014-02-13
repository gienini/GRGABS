package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladors.CLogin;
import controladors.CPersonal;
import controladors.CRegistre;
import controladors.Controller;

/**
 * Servlet implementation class Personal
 */
@WebServlet("/Personal")
public class Personal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext().getRealPath("/static");
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
		String params = "";
		PrintWriter pw = response.getWriter();
		
		/**
		 * Load the Personal page
		 */

		try {
			controlador = new CPersonal();
			pagina = controlador.getPagina(context);
		} catch (Exception e) {
			controlador = new CLogin();
			pagina = controlador.getPagina(context);
		}
		pw.print(pagina);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
