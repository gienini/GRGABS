package beans;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * Activitat del club 
 * @author Francesc
 *
 */
public class Activitat {
		private String nom;
		private String descripcio;
		private Calendar dia;
		private Timestamp hora;
		private String espai;
		private boolean senior;
		public Activitat()
		{
	
		}
		public Activitat(String nom, String descripcio, Calendar dia, Timestamp hora, String espai, boolean senior)
		{
			this.nom=nom;
			this.descripcio=descripcio;
			this.dia=dia;
			this.hora=hora;
			this.espai=espai;
			this.senior=senior;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getDescripcio() {
			return descripcio;
		}
		public void setDescripcio(String descripcio) {
			this.descripcio = descripcio;
		}
		public Calendar getData_realitzacio() {
			return dia;
		}
		public void setData_realitzacio(Calendar data_realitzacio) {
			this.dia = data_realitzacio;
		}
		public Timestamp getHora() {
			return hora;
		}
		public void setHora(Timestamp hora) {
			this.hora = hora;
		}
		public String getEspai() {
			return espai;
		}
		public void setEspai(String espai) {
			this.espai = espai;
		}
		public boolean isSenior() {
			return senior;
		}
		public void setSenior(boolean senior) {
			this.senior = senior;
		}
		
}
