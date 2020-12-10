package bookstore.controllers;

import bookstore.Entities.DashCommande;
import bookstore.Entities.DashCommande;
import bookstore.Utility.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashController implements Initializable {
    @FXML
    private ImageView img;


    @FXML
    private TableView<DashCommande> tabView;

    @FXML
    private TableColumn<DashCommande, Integer> numCom;

    @FXML
    private TableColumn<DashCommande, String> etat;

    @FXML
    private TableColumn<DashCommande, String> type;

    @FXML
    private TableColumn<DashCommande, String> cat;

    @FXML
    private TableColumn<DashCommande, Double> prix;

    @FXML
    private Button stati;

    public void getStat(ActionEvent actionEvent) throws IOException {

        DashCommande c =tabView.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("piechart.fxml"));

        Parent pt =  loader.load();

        PieChart pop = loader.getController();

        pop.build(c);

        Stage stage = new Stage();
        stage.setScene(new Scene(pt));
        stage.show();


    }
    ObservableList<DashCommande> Com = FXCollections.observableArrayList();
    public void getAll(){
        DashCommande com1   = new DashCommande(1, "Done",       "Achat","Comedy",   15);
        DashCommande com2   = new DashCommande(2, "In progress","Loan", "Adventure",100);
        DashCommande com3   = new DashCommande(3, "In progress","Achat","Comedy",   20);
        DashCommande com4   = new DashCommande(4, "In progress","Loan", "Adventure",60);
        DashCommande com5   = new DashCommande(5, "Done",       "Achat","Culture",  50);
        DashCommande com6   = new DashCommande(6, "Done",       "Loan", "History",  44);
        DashCommande com7   = new DashCommande(7, "In progress","Loan", "Geo",      33);
        DashCommande com8   = new DashCommande(8, "In progress","Achat","Comedy",   21);
        DashCommande com9   = new DashCommande(9, "Done",       "Loan", "Art",      11);
        DashCommande com10  = new DashCommande(10,"In progress","Achat","Comedy",   30);
        DashCommande com11  = new DashCommande(11,"In progress","Loan", "Society",  5.2);
        DashCommande com12  = new DashCommande(12,"Done",       "Achat","Politique",4.5);

        Com.add(com1);
        Com.add(com2);
        Com.add(com3);
        Com.add(com4);
        Com.add(com5);
        Com.add(com7);
        Com.add(com8);
        Com.add(com9);
        Com.add(com10);
        Com.add(com11);
        Com.add(com12);

        numCom.setCellValueFactory(new PropertyValueFactory<DashCommande,Integer>("numCom"));
        etat.setCellValueFactory(new PropertyValueFactory<DashCommande,String>("etat"));
        type.setCellValueFactory(new PropertyValueFactory<DashCommande,String>("typeDashCommande"));
        cat.setCellValueFactory(new PropertyValueFactory<DashCommande,String>("category"));
        prix.setCellValueFactory(new PropertyValueFactory<DashCommande,Double>("prix"));
        tabView.setItems(Com);
//        System.out.print(com1);

    }


    @FXML
    private javafx.scene.chart.PieChart  pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        img.setImage(new Image ("src/samlpe/m.jpg"));
        getAll();
        DAO cnx = new DAO();
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "SELECT * FROM stat  ";

            Statement s= cnx.getConnection().createStatement();
            ResultSet rsid = s.executeQuery(req1);
            while(rsid.next()){
                ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new javafx.scene.chart.PieChart.Data(rsid.getString("typeStat")+" : "+rsid.getInt("valueStat"), rsid.getInt("valueStat")),
                        new javafx.scene.chart.PieChart.Data("Money Spend : "+20, 20));
                        pieChart.setData(pieChartData);
                        pieChart.setClockwise(false);
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }
}
