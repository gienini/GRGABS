package beans;

import java.sql.Date;
import java.util.Calendar;

public class NoSenior extends Soci {

	public NoSenior(String dni, String nom, String cog1, String cog2, String adreca,
			Calendar data_naix, Calendar data_alta, String foto, String passw) {
		super(dni, nom, cog1, cog2, adreca, data_naix, data_alta, foto, passw );
	}

}
