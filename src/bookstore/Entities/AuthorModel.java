package bookstore.Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AuthorModel  {

    private Integer idAuth;
    private String firstAuth;
    private String lastAuth;
    private String bicAuth;

    public AuthorModel() {
    }

    public AuthorModel(int idAuth, String firstAuth, String lastAuth, String bicAuth) {
        this.idAuth = idAuth;
        this.firstAuth = firstAuth;
        this.lastAuth = lastAuth;
        this.bicAuth = bicAuth;
    }

    public int getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }

    public String getFirstAuth() {
        return firstAuth;
    }

    public void setFirstAuth(String firstAuth) {
        this.firstAuth = firstAuth;
    }

    public String getLastAuth() {
        return lastAuth;
    }

    public void setLastAuth(String lastAuth) {
        this.lastAuth = lastAuth;
    }

    public String getBicAuth() {
        return bicAuth;
    }

    public void setBicAuth(String bicAuth) {
        this.bicAuth = bicAuth;
    }
}
