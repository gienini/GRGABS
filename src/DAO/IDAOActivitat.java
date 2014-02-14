package DAO;

import java.util.List;

import beans.Activitat;
import beans.Soci;

public interface IDAOActivitat {
	public void add(Activitat activitat);
	public void update(Activitat activitat);
	public void delete(Activitat activitat);
	public List<Activitat> getAll();
	public List<Activitat> getAllNoSenior();
}
