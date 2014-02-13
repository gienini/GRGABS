package factories;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class GetConnection {

	/** Uses JNDI and Datasource (preferred style).   */
	public Connection getJNDIConnection(){
		String DATASOURCE_CONTEXT = "MySQLconnection";

		Connection result = null;
		try {
			Context initialContext = new InitialContext();
			if ( initialContext == null){
				log("JNDI problem. Cannot get InitialContext.");
			}
			DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
			if (datasource != null) {
				result = datasource.getConnection();
			}
			else {
				log("Failed to lookup datasource.");
			}
		}
		catch ( NamingException ex ) {
			log("Cannot get connection: " + ex);
		}
		catch(SQLException ex){
			log("Cannot get connection: " + ex);
		}
		return result;
	}

	private static void log(Object aObject){
		System.out.println(aObject);
	}
}
																													