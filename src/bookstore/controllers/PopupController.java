package bookstore.controllers;

import bookstore.Entities.AuthorModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import bookstore.Utility.DAO;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PopupController implements Initializable {
    DAO cnx = new DAO();
    @FXML
    private TableView<AuthorModel> tabView;

    @FXML
    public TableColumn<AuthorModel,Integer> idAuth;

    @FXML
    public TableColumn<AuthorModel,String> firstAuth;

    @FXML
    public TableColumn<AuthorModel,String> lastAuth;

    @FXML
    public TableColumn<AuthorModel,String> bicAuth;
    @FXML
    private TextField popid;


    @FXML
    private TextField popfirst;

    @FXML
    private TextField poplast;

    @FXML
    private TextField popbic;

    @FXML
    private Button cancel;

    @FXML
    private Button update;

    public void tranfer (AuthorModel auth){
        popid.setText(String.valueOf(auth.getIdAuth()));
        popfirst.setText(auth.getFirstAuth());
        poplast.setText(auth.getLastAuth());
        popbic.setText(auth.getBicAuth());

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    public void Update(ActionEvent actionEvent) throws IOException {

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "UPDATE  author set nomAuthor = ? ,prenomAuthor = ? ,bicAuthor = ? WHERE idAuthor = "+ popid.getText();
            PreparedStatement ps = cnx.getConnection().prepareStatement(req1);


            ps.setString(1, popfirst.getText());
            ps.setString(2, poplast.getText());
            ps.setString(3, popbic.getText());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex);
        }



    }

    public void cancel(ActionEvent actionEvent) {
    }
}
