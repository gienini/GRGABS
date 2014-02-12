package beans;

import java.sql.Date;
import java.util.Calendar;

public class Soci {
	private String dni;
	private String nom;
	private String cog1;
	private String cog2;
	private String adreca;
	private Calendar data_naix;
	private Calendar data_alta;
	private String foto;
	
	public Soci() {
		
	}
	public Soci(String dni, String nom, String cog1, String cog2, String adreca, Calendar data_naix, Calendar data_alta, String foto) {
		this.dni=dni;
		this.nom=nom;
		this.cog1=cog2;
		this.cog2=cog2;
		this.adreca=adreca;
		this.data_naix=data_naix;
		this.data_alta=data_alta;
		this.foto=foto;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCog1() {
		return cog1;
	}
	public void setCog1(String cog1) {
		this.cog1 = cog1;
	}
	public String getCog2() {
		return cog2;
	}
	public void setCog2(String cog2) {
		this.cog2 = cog2;
	}
	public String getAdreca() {
		return adreca;
	}
	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}
	public Calendar getData_naixement() {
		return data_naix;
	}
	public void setData_naixement(Calendar data_naixement) {
		this.data_naix = data_naixement;
	}
	public Calendar getData_alta() {
		return data_alta;
	}
	public void setData_alta(Calendar data_alta) {
		this.data_alta = data_alta;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
