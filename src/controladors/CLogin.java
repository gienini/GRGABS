package controladors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CLogin implements Controller {

	private String retorn;

	public CLogin() {
		retorn = "";

	}

	@Override
	public String getPagina(String contextPath) {

		try {
			retorn = HTMLReader.getFile(new FileInputStream(contextPath
					+ "/header.html"));
			retorn = retorn+HTMLReader.getFile(new FileInputStream(contextPath
					+ "/login.html"));
			retorn = retorn+HTMLReader.getFile(new FileInputStream(contextPath
					+ "/footer.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return retorn;
	}

}
