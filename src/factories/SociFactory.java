package factories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import beans.NoSenior;
import beans.Senior;
import beans.Soci;

public class SociFactory {
	public Soci crearSoci(String dni, String nom, String cog1, String cog2, String adreca, Date data_naix, Date data_alta, String foto) {
		
		
		/*** Actual Date***/
		/*** create a java calendar instance***/
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();

		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		/***Compare the dates to determine if the Soci is Senior or not ***/
		
		if((date.getTime()-data_naix.getTime()/1000)>(365*2)){
			return new Senior(dni, nom,cog1,cog2,adreca,data_naix ,data_alta, foto);
		} else {
			return new NoSenior(dni, nom,cog1,cog2,adreca,data_naix ,data_alta, foto);
		}
		/*return new Soci(nom,cog1,cog2,adreca,data_naix ,data_alta, foto);*/
	}
}
