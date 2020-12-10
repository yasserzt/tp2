/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.Entities;

import java.util.Date;

/**
 *
 * @author i__t__s
 */
public class Commande {
    private int id;
    private String creation_date;
    private String description;
    private String status;

    public Commande(int id, String creation_date, String description, String status) {
        this.id = id;
        this.creation_date = creation_date;
        this.description = description;
        this.status = status;
    }

    public Commande() {
    }

    public Commande(String description, String status) {
        this.description = description;
        this.status = status;
    }

    public Commande(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", creation_date=" + creation_date + ", description=" + description + ", status=" + status + '}';
    }
    
    
}
