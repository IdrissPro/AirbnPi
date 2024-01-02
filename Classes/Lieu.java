import java.util.HashMap;
public class Lieu {

    //Lieu générique avec une adresse et une liste de voisins(leur distance est donnée en valeur dans le map)
    private String adresse;

    public Lieu(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
