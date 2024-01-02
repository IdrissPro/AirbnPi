import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Appartement extends Lieu { //Un lieu est composé d'une adresse

    //Un appartement est un lieu avec une liste de tâches et une liste de locataires
    private ArrayList<Tâche> taches;
    private ArrayList<Locataire> locataires;

    public Appartement(String adresse) {//On déclare un appartement avec son adresse
        super(adresse);
        this.taches = new ArrayList<>();
        this.locataires = new ArrayList<>();
    }

    public void addLocataire(Locataire locataire) {//On ajoute un locataire à la liste des locataires
        locataires.add(locataire);
    }

    public void setListeLocataires(ArrayList<Locataire> locataires) {
        this.locataires = locataires;
    }

    public ArrayList<Locataire> getListeLocataires() {
        return locataires;
    }

    public void setTaches(ArrayList<Tâche> taches) {
        this.taches = taches;
    }


    public ArrayList<Tâche> getTaches() {
        return taches;
    }

    public Tâche getTache(String nom) {//On récupère une tâche par son nom
        for (Tâche tache : taches) {
            if (tache.getNom().equals(nom)) {
                return tache;
            }
        }
        return null;
    }

    public void ajouterTache(Tâche tache) {//On ajoute une tâche à la liste des tâches
        taches.add(tache);
        //on l'ajoute au fichier tâches.txt
        try {
            PrintWriter pwFichier = new PrintWriter(new FileWriter("taches.txt", true));
            pwFichier.println(tache.getNom() + " " + tache.getStatut() + " " + tache.getAttribution());
            pwFichier.close();
        } catch (Exception e) {
        }
    }

    
} 
