package controladors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CRegistre implements Controller {
	
	private String retorn;
	
	public CRegistre(){
		retorn = "";
	}
 
	@Override
	public String getPagina(String s) {
		try {
			retorn = HTMLReader.getFile(new FileInputStream(s
					+ "WebContent/static/header.html"));
			retorn = HTMLReader.getFile(new FileInputStream(s
					+ "WebContent/static/registre.html"));
			retorn = HTMLReader.getFile(new FileInputStream(s
					+ "WebContent/static/footer.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return retorn;
	}

}
