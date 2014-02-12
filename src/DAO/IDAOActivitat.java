package DAO;

import beans.Activitat;
import beans.Soci;

public interface IDAOActivitat {
	public void add(Activitat activitat);
	public void update(Activitat activitat);
	public void delete(int idActivitat);

}
