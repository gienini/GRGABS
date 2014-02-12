package DAO;

import beans.Soci;

public interface IDAO {
		
		public void add(Soci soci);
		public void update(Soci Soci);
		public void delete(String Dni);
	}
