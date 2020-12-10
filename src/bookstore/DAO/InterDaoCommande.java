/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAO;

import bookstore.Entities.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author i__t__s
 */
public interface InterDaoCommande {
    public void ajouterCommande(Commande c);
    public void  editerCommande(Commande c);
    public void supprimerCommande(Commande c);
    public List<Commande> getAllCommandes() throws SQLException;
    public Commande getCommande(Commande c);
}
