import java.util.ArrayList;

public class AppartementPartage {//Cette classe créée un appartement partagé par tous les utilisateurs
    
    private static Appartement appartementPartage;

    public static synchronized Appartement getAppartementPartage() {//s'il existe déjà, on ne recréé pas un appartement
        if (appartementPartage == null) {
            appartementPartage = new Appartement("adresse");
            // Initialisation des autres propriétés de l'appartement
            ArrayList<Tâche> taches = new ArrayList<Tâche>();
            taches=Charger.chargerTaches("taches.txt");//On charge les tâches de l'appartement depuis le fichier tâches.txt
			AppartementPartage.appartementPartage.setTaches(taches);
        }
        return appartementPartage;
    }

    public static synchronized void setAppartementPartage(Appartement appartementPartage) {
        AppartementPartage.appartementPartage = appartementPartage;
    }
}