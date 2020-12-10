/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author i__t__s
 */
public class SingletonConnection {
   // private String url="jdbc:mysql://localhost:3306/trial";
    private String user="root";
    private String pwd="";
    private Connection connexion;
    private static SingletonConnection instance;
    
    private String url ="jdbc:mysql://localhost:3306/trial" +
            "?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    
    private SingletonConnection()
    {
        
        try{
            connexion=DriverManager.getConnection(url,user,pwd);
            System.out.println("connexion à la base de données reussite");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static SingletonConnection getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonConnection();
        }
        return instance;
    }
    
    public Connection getCon()
    {
        return connexion;
    }
    
}
