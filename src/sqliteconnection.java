import java.sql.*;
import javax.swing.*;
public class sqliteconnection {
	
	Connection conn = null;
	public static Connection dbconnector() 
	{
		try {			
			Class.forName("org.sqlite.JDBC");				
			Connection conn = DriverManager.getConnection("jdbc:sqlite:wstoreDB.db");
			return conn;
		}catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Error en la conexión");
			return null;
		}
	}
	
}
