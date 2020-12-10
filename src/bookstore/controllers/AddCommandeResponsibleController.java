/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.controllers;

import bookstore.DAO.DaoCommande;
import bookstore.Entities.Commande;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author i__t__s
 */
public class AddCommandeResponsibleController implements Initializable {
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtNumero;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtStatus;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DaoCommande serviceCommande = new DaoCommande();
        Integer nb=0;
        try
        {
            nb= serviceCommande.getCount();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDate now = LocalDate.now();
        System.out.println(now);
        txtNumero.setText(nb.toString());
        txtDate.setValue(now);
        txtStatus.setText("Validated");
    }    

    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
 
        stage.close();
    }

    @FXML
    private void onClickAjouter(ActionEvent event) {
        Commande c=new Commande();
        c.setCreation_date(txtDate.getValue().toString());
        c.setId(Integer.parseInt(txtNumero.getText()));
        c.setDescription(txtDescription.getText());
        c.setStatus(txtStatus.getText());
        
        DaoCommande serviceCommande = new DaoCommande();
        serviceCommande.ajouterCommande(c);
        
    }
    
}
