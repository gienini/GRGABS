package factories;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import beans.NoSenior;
import beans.Senior;
import beans.Soci;

public class SociFactory {
	public Soci crearSoci(String dni, String nom, String cog1, String cog2,
			String adreca, Calendar data_naix, Calendar data_alta, String foto, String passw) {
		Calendar actual = new GregorianCalendar();
		actual.setTime(new Date());
		int any_actual = actual.get(actual.YEAR);
		int any_alta = data_alta.get(data_alta.YEAR);
	
		
		if (any_actual - any_alta > 2) {
			return new Senior(dni, nom, cog1, cog2, adreca, data_naix,
					data_alta, foto, passw);
		} else {
			return new NoSenior(dni, nom, cog1, cog2, adreca, data_naix,
					data_alta, foto, passw);
		}

	}
}
