package controladors;

public class CActivitats implements Controller{

	@Override
	public String getPagina(String s) {
		//Passem el context per par�metre, d'aquesta manera obtenim el path del contingut est�tic
		/*** Header ***/
		String page = "header" ;
		/***Show the list of activities ***/			
		page += getSociActivitats();
		/*** Footer ***/
		page += "footer";
		return page;
	}

	private String getSociActivitats() {
		// TODO Auto-generated method stub
		return null;
	}

}
