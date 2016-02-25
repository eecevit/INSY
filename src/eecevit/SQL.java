package eecevit;

import java.sql.*;
import org.postgresql.ds.PGSimpleDataSource;



public class SQL {
	
	public static  String u;
	public static String s;
	public static String p;
	public static String pw;
	public static String d;
	
	public void connect(){
		/*
		* Mit PGSimpleDataSource wird ein einfaches Datasource erstellt
		*
		* via dem Befehl ServerName("...") wird die IP von der Datenbank angegeben
		* via dem Befehl DatabaseName("...") wird angegeben auf welche Datenbank zugegriffen wird
		* via dem Befehl setUser("...") wird festgelegt mit welchem User auf diese Datenbank zugegriffen werden soll
		* via dem Befehl setPassword("...") wird angegeben welchs Password der User in der Datenbank verwendet
		*
		*/
		PGSimpleDataSource ds = new PGSimpleDataSource();
		   ds.setServerName(s);
		   ds.setDatabaseName(d);
		   ds.setUser(u);
		   ds.setPassword(pw);

			// Verbindung herstellen
		   try(	Connection con = ds.getConnection();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM person");
				/*
				 * 1.) 	Dank der neuen Moeglichkeit die einzelnen Variablen im parameter vom
				 * 		try eingeben koenen, muss man es am ende von try{...} nicht mehr einzeln
				 * 		schliessen
				 * 		
				 * 		Connection 		-->  	eine Verbindung zu der Datenbank wird aufgebaut
				 * 		Statement 		-->		ist ein Befehl um weitere Informationen zu senden
				 * 		executeQuery	-->		hier wird einfach der SQL Befehl eingegegeben und im rs abgespeichert
				 *
				 */
				
				){
				   while (rs.next()) { // mit .next() kommt man zu der naechsten Ausgabe
					   
					   String wert = rs.getString(2);
					   System.out.println(wert);
					  
				   } 
				   }catch(SQLException e){
					   /*
					    * es ist wichtig eine Exception einzubauen, da es immer zu Fehlern kommen kann
					    * und damit es nicht dramatische folgen haben soll wird dieser gefiltert
					    */
					   e.printStackTrace();
				   }
	}
}
 