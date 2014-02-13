package controladors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import factories.SociFactory;
import beans.Soci;

public class CPersonal implements Controller{

	@Override
	public String getPagina(String s) {
		//Passem el context per paràmetre, d'aquesta manera obtenim el path del contingut estàtic
		/*** Header ***/
		String page = "" ;
		/*** Footer ***/
		/***page += "footer";**/
		try {
			/*** Header ***/
			page = HTMLReader.getFile(new FileInputStream(s
					+ "/header.html"));
			/*** Soci Persnal Info ***/
			page = page+getSociPersonalInfo(s);			
			/*** Soci Personal Activities ***/
			//page = page+getSociPersonalActivitats(s);
			/*** Footer ***/
			page = page+HTMLReader.getFile(new FileInputStream(s
					+ "/footer.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return page;
		
	}

	private String getSociPersonalActivitats(String s) {
		String content = "content";
		try {
			content = HTMLReader.getFile(new FileInputStream(s
					+ "/login.html"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return content;
	}

	private String getSociPersonalInfo(String s) {

		String content = "";
		String line = "";
		
		/***Get the soci values **/
		String name = "David";
		String cog1 = "Rubio";
		String dni = "47845639G";
		String adreca = "Carrer del mig";
		String edat = "26";
		try {
			BufferedReader read = new BufferedReader(new FileReader(s + "/profile.html"));
			try {
				line = read.readLine();
				while(line != null){
					//We need to find if the line contains a value
					if(line.contains("%personal:variable%")){
						line = line.replaceAll("%personal:variable%", "Hello World");
					} else if (line.contains("%personal:name%")){
						line = line.replaceAll("%personal:name%", name);
					} else if (line.contains("%personal:cog1%")){
						line = line.replaceAll("%personal:cog1%", cog1);
					} else if (line.contains("%personal:dni%")){
						line = line.replaceAll("%personal:dni%", dni);
					} else if (line.contains("%personal:adreca%")){
						line = line.replaceAll("%personal:adreca%", adreca);
					} else if (line.contains("%personal:edat%")){
						line = line.replaceAll("%personal:edat%", edat);
					} 
					content = content + line;
					line = read.readLine();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return content;
	}

}

