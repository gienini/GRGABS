package controladors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CLogin implements Controller {

	private String retorn;

	public CLogin() {
		retorn = "";

	}

	@Override
	public String getPagina(String s) {

		try {
			retorn = HTMLReader.getFile(new FileInputStream(s
					+ "/header.html"));
			retorn = retorn+HTMLReader.getFile(new FileInputStream(s
					+ "/login.html"));
			retorn = retorn+HTMLReader.getFile(new FileInputStream(s
					+ "/footer.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return retorn;
	}

}
