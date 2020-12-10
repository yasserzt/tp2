/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAO;

import bookstore.Entities.Commande;
import bookstore.Utility.SingletonConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author i__t__s
 */
public class DaoCommande implements InterDaoCommande{
    
    SingletonConnection cnx=SingletonConnection.getInstance();
    
    @Override
    public void ajouterCommande(Commande c) {
        String date_creation = c.getCreation_date();
        String description = c.getDescription();
        String status = c.getStatus();
        
        String sql="INSERT INTO commande VALUES (null,'"+date_creation+"','"+description+"','"+status+"')";
      //  String sql="INSERT INTO commande VALUES (null,'2020/12/03','a small description','Ongoing')";
        Connection conn = cnx.getCon();
        Statement st;
            try
            {
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Insertion reussite");
            }
            catch (SQLException ex)
            {
                System.out.println("Erreure d'insertion !");
                ex.getMessage();
            }
    }

    @Override
    public void editerCommande(Commande c) {
        int idCommande = c.getId();
    //    DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
        String dateCreation = c.getCreation_date();
        String description = c.getDescription();
        String status = c.getStatus();
        
       // String sql="UPDATE commande SET id='"+idCommande+"', description ='"+description+"', status='"+status+"' WHERE id="+idCommande;
        String sql="UPDATE commande SET status='Validated' WHERE id="+idCommande;
        Connection conn=cnx.getCon();
        Statement st;
            try {
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Modification reussite");
            } catch (SQLException ex) {
                System.out.println("Probleme de modification");
                ex.getMessage();
            }
    }

    @Override
    public void supprimerCommande(Commande c) {
        String sql = "DELETE FROM commande WHERE id ="+c.getId()+";";
        Connection conn=cnx.getCon();
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println("Probleme de suppression !!!");
            }
    }

    @Override
    public List<Commande> getAllCommandes() throws SQLException{
        List<Commande> listCommande = new ArrayList();
        String sql="SELECT * FROM commande";
        Connection conn=cnx.getCon();
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            String creation_date = res.getDate("creation_date").toString();
            String description = res.getString("description");
            String status = res.getString("status");
            Commande c=new Commande(id,creation_date,description,status);
           
            listCommande.add(c);
        }
            return listCommande;
    }

    @Override
    public Commande getCommande(Commande c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getCount()  throws SQLException{
        int nb=0;
        String sql="SELECT * FROM commande";
        Connection conn=cnx.getCon();
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
           nb++;
        }
            return nb;
    }
    
}
