package bookstore.controllers;

import bookstore.DAO.DaoCommande;
import bookstore.Entities.AuthorModel;
import bookstore.Utility.DAO;
import bookstore.Utility.SingletonConnection;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;


public class AuthorController  implements Initializable{
    DAO cnx = new DAO();
    @FXML
    private Label lab;
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
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField bic;

    @FXML
    private Button button;







    ObservableList<AuthorModel> authList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            String req1= "select * from author ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
                while(rs.next())
                {
                    authList.add(new AuthorModel(
                             rs.getInt("idAuthor")
                            ,rs.getString("nomAuthor")
                            ,rs.getString("prenomAuthor")
                            ,rs.getString("bicAuthor"))
                    );
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }

        idAuth.setCellValueFactory(new PropertyValueFactory<>("idAuth"));
        firstAuth.setCellValueFactory(new PropertyValueFactory<>("firstAuth"));
        lastAuth.setCellValueFactory(new PropertyValueFactory<>("lastAuth"));
        bicAuth.setCellValueFactory(new PropertyValueFactory<>("bicAuth"));
        tabView.setItems(authList);


    }

    public void Btn(ActionEvent actionEvent) {
        try {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            if(firstName.getText().equals("") || lastName.getText().equals("") || bic.getText().equals("")){
                lab.setText("Champs require");
            }else{
                String req= "insert into author (nomAuthor,prenomAuthor,bicAuthor) values (?,?,?)";
                PreparedStatement ps = cnx.getConnection().prepareStatement(req);


                ps.setString(1, firstName.getText());
                ps.setString(2, lastName.getText());
                ps.setString(3, bic.getText());
                ps.executeUpdate();
                String req1= "select max(idAuthor) from author  ";
                Statement s= cnx.getConnection().createStatement();
                ResultSet rsid = s.executeQuery(req1);


                while(rsid.next()){

                        authList.add(new AuthorModel(
                                rsid.getInt("max(idAuthor)")
                                , firstName.getText()
                                , lastName.getText()
                                , bic.getText()
                        ));

                }


            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        idAuth.setCellValueFactory(new PropertyValueFactory<>("idAuth"));
        firstAuth.setCellValueFactory(new PropertyValueFactory<>("firstAuth"));
        lastAuth.setCellValueFactory(new PropertyValueFactory<>("lastAuth"));
        bicAuth.setCellValueFactory(new PropertyValueFactory<>("bicAuth"));
        tabView.setItems(authList);
    }

    public void DeleteAuthor(ActionEvent actionEvent){
        AuthorModel author = tabView.getSelectionModel().getSelectedItem();

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "DELETE FROM author WHERE idAuthor = "+ author.getIdAuth();
            Statement s= cnx.getConnection().createStatement();

            s.executeUpdate(req1);

            tabView.getItems().remove(author);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }




    public void Edit(ActionEvent actionEvent) throws IOException {
        AuthorModel c =tabView.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookstore.fxml.PopupView.fxml"));

        Parent pt =  loader.load();

        PopupController pop = loader.getController();

        pop.tranfer(c);

        Stage stage = new Stage();
        stage.setScene(new Scene(pt));
        stage.show();
    }

    public void up(AuthorModel auth) {

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "UPDATE  author set nomAuthor = ? ,prenomAuthor = ? ,bicAuthor = ? WHERE idAuthor = "+ auth.getIdAuth();
            PreparedStatement ps = cnx.getConnection().prepareStatement(req1);


            ps.setString(1, auth.getFirstAuth());
            ps.setString(2, auth.getLastAuth());
            ps.setString(3, auth.getBicAuth());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
//
}
