/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacteditor;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public class ContactEditor {

    public static Connection getJDBCConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
	return DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=Sanjukta@12345");
    }
    
}
