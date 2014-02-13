package controladors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CRegistre implements Controller {
	
	private String retorn;
	
	public CRegistre(){
		retorn = "";
	}
 
	@Override
	public String getPagina(String contextPath) {
		String path = "staticlogin/";
		try {
			retorn = HTMLReader.getFile(new FileInputStream(contextPath + path
					+ "/header.html"));
			retorn = retorn
					+ HTMLReader.getFile(new FileInputStream(contextPath + path
							+ "/login.html"));
			retorn = retorn
					+ HTMLReader.getFile(new FileInputStream(contextPath + path
							+ "/footer.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return retorn;
	}

}
