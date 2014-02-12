package factories;

import java.sql.Date;
import java.sql.Timestamp;

import beans.Activitat;

public class ActivitatFactory {
	
	public Activitat creaActivitat(String nom, String descripcio, Date dia, Timestamp hora, String espai, boolean senior) {
		return new Activitat(nom,descripcio,dia,hora,espai,senior);
	}
	{
		
	}
}
