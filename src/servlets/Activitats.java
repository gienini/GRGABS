package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Activitats extends HttpServlet {
	private String context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext().getRealPath("/");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();

		try {

			if (req.getSession().getAttribute("login").equals("nologin")) {
				resp.sendRedirect("/GRGABS/login");

			}
		} catch (Exception e) {
			resp.sendRedirect("/GRGABS/login");
		}

	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub

	}

}
