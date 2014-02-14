package controladors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * Classe controlador de les vistes de login
 * @author Fran Gienini
 *
 */
public class CLogin implements Controller {

	private String retorn;

	public CLogin() {
		retorn = "";

	}

	@Override
	public String getPagina(String contextPath) {
		contextPath = contextPath + "staticlogin/";
		try {
			retorn = HTMLReader.getFile(new FileInputStream(contextPath
					+ "/header.html"));
			retorn = retorn
					+ HTMLReader.getFile(new FileInputStream(contextPath
							+ "/body.html"));
			retorn = retorn
					+ HTMLReader.getFile(new FileInputStream(contextPath
							+ "/footer.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return retorn;
	}

	@Override
	public String getPagina(String s, String dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
