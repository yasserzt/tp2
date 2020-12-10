package bookstore.Entities;

public class DashCommande {

    private int numCom;
    private String etat;
    private String typeCommande;
    private String category;
    private double prix;

    public DashCommande() {
    }

    public DashCommande(int numCom, String etat, String typeCommande, String category, double prix) {
        this.numCom = numCom;
        this.etat = etat;
        this.typeCommande = typeCommande;
        this.category = category;
        this.prix = prix;
    }

    public int getNumCom() {
        return numCom;
    }

    public void setNumCom(int numCom) {
        this.numCom = numCom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTypeCommande() {
        return typeCommande;
    }

    public void setTypeCommande(String typeCommande) {
        this.typeCommande = typeCommande;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
