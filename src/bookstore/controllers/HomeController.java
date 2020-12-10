/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author i__t__s
 */
public class HomeController implements Initializable {
    @FXML
    private Button BtnExit;
    @FXML
    private Button btnOuvrages;
    @FXML
    private Button btnEmpreintes;
    @FXML
    private Button btnLivraisons;
    @FXML
    private Button btnClients;
    @FXML
    private Button btnCommandes;
    @FXML
    private Button btnReclamations;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == BtnExit) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You Are about to Exit the application");
            alert.setContentText("Are you sure to Exit ?");
            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeTwo = new ButtonType("No");
//            ButtonType buttonTypeThree = new ButtonType("Three");
//            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                System.exit(0);
            } 
            else {
                // ... user chose No or closed the dialog
            }
            
        } else if (mouseEvent.getSource() == btnOuvrages) {
            loadStage("/bookstore/fxml/DashView.fxml");
            System.out.println("ouvrages window");
        } else if (mouseEvent.getSource() == btnEmpreintes) {
           // loadStage("/bookstore/fxml/GUIEmpreintes.fxml");
            System.out.println("Empreintes window");
        } else if (mouseEvent.getSource() == btnLivraisons) {
            loadStage("../fxml/LivraisonGUI.fxml");
           // System.out.println("Livraison window");
        } else if (mouseEvent.getSource() == btnClients) {
           // loadStage("/bookstore/fxml/GUIClients.fxml");
            System.out.println("Clients window");
        } else if (mouseEvent.getSource() == btnCommandes) {
            loadStage("../fxml/CommandeGUI.fxml");
          //  System.out.println("commandes window");
        } else if (mouseEvent.getSource() == btnReclamations) {
           // loadStage("test.fxml");
            System.out.println("reclamations window");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    
    private void loadStage(String fxml) {
        try {
          //  System.out.println(getClass().getResource("..").toString());
          //  System.out.println(getClass().getResource(fxml).toString());
         //   System.out.println(getClass().getResource(".."+fxml).toString());
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
