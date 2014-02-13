package controladors;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Clase per a la gestio de fitxers HTML
 * @author Francisco Javier Gienini
 *
 */
public class HTMLReader {
/**
 * Llegeix un fitxer HTML i el fica a una String
 * @param input fitxer a llegir
 * @return una cadena de text amb el contingut del fitxer 
 */
	public static String getFile(FileInputStream input) {

		DataInputStream di = new DataInputStream(input);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(di));
		String retorn = "", strBuffer = "";

		try {
			while ((strBuffer = buffer.readLine()) != null) {
				retorn = retorn + strBuffer;
			}
			input.close();
			return retorn;

		} catch (IOException e) {
			e.printStackTrace();
			return retorn;

		}
	}

}
