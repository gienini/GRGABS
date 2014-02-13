package DAO;

import beans.Soci;
import beans.Activitat;

public interface IDAOSoci {
		public void add(Soci soci);
		public void update(Soci soci);
		public void delete(Soci soci);
		public Soci getSoci(String dni);
		public boolean isLogin(String Dni, String password);
		public void joinActivitat(Soci soci, Activitat act);
		public void leaveActivitat(Soci soci, Activitat act);
		public boolean isOnActivitat(Soci soci, Activitat act);
		public Activitat[] getActivitats(Soci soci);
	}
