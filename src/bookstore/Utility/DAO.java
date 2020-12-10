package bookstore.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    public String url="jdbc:mysql://localhost:3306/ewa";
    public String login="root";
    public String pwd="";
    public Connection connexion;
    public static DAO instance;

    public DAO()
    {
        try {
            connexion=DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DAO getIstance()
    {
        if(instance==null)
            instance=new DAO();

        return instance;
    }
    public Connection getConnection()
    {
        return connexion;
    }
}
