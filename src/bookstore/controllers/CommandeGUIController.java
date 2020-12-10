/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.controllers;

import bookstore.DAO.DaoCommande;
import bookstore.Entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import outerService.Mailing;

/**
 * FXML Controller class
 *
 * @author i__t__s
 */
public class CommandeGUIController implements Initializable {
    @FXML
    private Button btnClose;
    @FXML
    private TableColumn<Commande, Integer> id;
    @FXML
    private TableColumn<Commande, String> creation_date;
    @FXML
    private TableColumn<Commande, String> description;
    @FXML
    private TableColumn<Commande, String> status;
    
    ObservableList<Commande> MesCommandes = FXCollections.observableArrayList();
    @FXML
    private TableView<Commande> TableCommandes;
    @FXML
    private Button btnVALIDATE;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnDELETE;
    @FXML
    private Button btnREFRESH;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillTableView();
        
    }
    
    
    private void fillTableView()
    {
        DaoCommande serviceCommande=new DaoCommande();
        try
        {
        MesCommandes.addAll(serviceCommande.getAllCommandes());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        creation_date.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableCommandes.setItems(MesCommandes);
    }
    

    @FXML
    private void closeButtonAction(ActionEvent event) {

        Stage stage = (Stage) btnClose.getScene().getWindow();
 
        stage.close();
    }

    @FXML
    private void onClickValidate(ActionEvent event) {
        System.out.println(TableCommandes.getSelectionModel().getSelectedItem().getStatus());
        if (TableCommandes.getSelectionModel().getSelectedItem().getStatus().equals("Validated"))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Validation Commande");
            alert.setContentText("cette commande est déjà validée !!");

            alert.showAndWait();
        }
        else
        {
            DaoCommande serviceCommande=new DaoCommande();
            Commande c = TableCommandes.getSelectionModel().getSelectedItem();
            serviceCommande.editerCommande(c);
            // if  client turned on Notifications for commande
            String content="votre commande "+c.toString()+" est maintenant VALIDEE !";
            Mailing.sendMail("farm3items@gmail.com",content);
            TableCommandes.getItems().clear();
            fillTableView();
        }
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/AddCommandeResponsible.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();   
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickDelete(ActionEvent event) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You Are about to Delete an order !");
        alert.setContentText("Do you want to proceed ?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");
//            ButtonType buttonTypeThree = new ButtonType("Three");
//            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            DaoCommande serviceCommande=new DaoCommande();
            Commande c = TableCommandes.getSelectionModel().getSelectedItem();
            serviceCommande.supprimerCommande(c);
            // if  client turned on Notifications for commande
            String content="votre commande "+c.toString()+" est REJETEE !";
            Mailing.sendMail("farm3items@gmail.com",content);
            TableCommandes.getItems().clear();
            fillTableView();
        } 
        else {
            // ... user chose No or closed the dialog
        }
    }

    @FXML
    private void onClickREFRESH(ActionEvent event) {
        TableCommandes.getItems().clear();
        fillTableView();
    }
    
}
