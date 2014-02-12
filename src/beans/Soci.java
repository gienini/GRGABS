package beans;

import java.sql.Date;

public class Soci {
	private String nom;
	private String cog1;
	private String cog2;
	private String adreca;
	private Date data_naix;
	private Date data_alta;
	private String foto;
	
	public Soci() {
		
	}
	public Soci(String nom, String cog1, String cog2, String adreca, Date data_naix, Date data_alta, String foto) {
		this.nom=nom;
		this.cog1=cog2;
		this.cog2=cog2;
		this.adreca=adreca;
		this.data_naix=data_naix;
		this.data_alta=data_alta;
		this.foto=foto;
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
	public Date getData_naixement() {
		return data_naix;
	}
	public void setData_naixement(Date data_naixement) {
		this.data_naix = data_naixement;
	}
	public Date getData_alta() {
		return data_alta;
	}
	public void setData_alta(Date data_alta) {
		this.data_alta = data_alta;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
