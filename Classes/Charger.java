import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Charger {//Cette classe permet de charger les données depuis les fichiers

    public static Map<String, String> chargerListeLocataire(String cheminFichier){//On charge un dictionnaire de locataire stocké dans un fichier
        Map<String, String> listeLoc = new HashMap<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] locataireInfo = ligne.split(" ");
                listeLoc.put(locataireInfo[0], locataireInfo[1]);
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        
        return listeLoc;
    }

    public static Map<String, String> chargerListeProprietaire(String cheminFichier){//On charge un dictionnaire de propriétaire stocké dans un fichier (1 seul propriétaire dans notre cas)
        Map<String, String> listeProp = new HashMap<>();
        
        try  {
            BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] proprietaireInfo = ligne.split(" ");
                listeProp.put(proprietaireInfo[0], proprietaireInfo[1]);
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        
        return listeProp;
    }

    public static ArrayList<Tâche> chargerTaches(String cheminFichier) {//On charge une liste de tâches stockée dans un fichier
        ArrayList<Tâche> listeTaches = new ArrayList<>();
        
        try  {
            BufferedReader reader = new BufferedReader(new FileReader(cheminFichier));
            String ligne;

            while ((ligne = reader.readLine()) != null) {//Parcours du fichier ligne par ligne, chaque ligne correspond à une tâche
                String[] elements = ligne.split(" ");

                if (elements.length == 3) {//Si la ligne contient 3 éléments, on récupère le nom, le statut et l'attribution de la tâche
                    String nom = elements[0];
                    int statut = Integer.parseInt(elements[1]);
                    String attribution = elements[2];

                    Tâche tache = new Tâche(nom, statut, attribution);
                    listeTaches.add(tache);
                } else if (elements.length == 2) {//Si la ligne contient 2 éléments, on récupère le nom et le statut de la tâche
                    String nom = elements[0];
                    int statut = Integer.parseInt(elements[1]);
                    Tâche tache = new Tâche(nom, statut, "");
                    listeTaches.add(tache);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return listeTaches;
    }

    public static void copierContenuFichier(String fichierSource, String fichierDestination) {
        try (BufferedReader brSource = new BufferedReader(new FileReader(fichierSource));
             BufferedWriter bwDestination = new BufferedWriter(new FileWriter(fichierDestination, false))) {

            String ligne;
            while ((ligne = brSource.readLine()) != null) {
                bwDestination.write(ligne);
                bwDestination.newLine(); // Ajoute une nouvelle ligne après chaque ligne copiée
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}