import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Identification {

    //listes de locataires et de propriétaires avec leur mot de passe
    private static Map<String, String> listeLoc;
    private static Map<String, String> listeProp;

    // Initialisation des listes
    static {
        listeLoc = new HashMap<>();
        listeProp = new HashMap<>();
    }

    public static void setListeLoc(Map<String, String> listeLoc) {
        Identification.listeLoc = listeLoc;
    }

    public static void setListeProp(Map<String, String> listeProp) {
        Identification.listeProp = listeProp;
    }

    // Ajout d'un locataire ou d'un propriétaire à la main (pour les tests)
    public static void ajouterLocataire(String nom, String mdp) {
        listeLoc.put(nom, mdp);
    }

    // Ajout d'un locataire ou d'un propriétaire à la main (pour les tests)
    public static void ajouterProprietaire(String nom, String mdp) {
        listeProp.put(nom, mdp);
    }

    // Identification d'un locataire ou d'un propriétaire
    public static String identification(Boolean estLocataire, Socket socket) {
        PrintWriter pw = null;
        BufferedReader br = null;
        String nom = "";

        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw.println("Bienvenue, Veuillez entrer votre nom");
            pw.println("");
            nom = br.readLine();
            // Vérification que le nom est dans la liste des locataires ou des propriétaires
            while (!(listeLoc.containsKey(nom) || listeProp.containsKey(nom))) {
                pw.println("Nom inconnu, veuillez entrer un autre nom");
                pw.println("");
                nom = br.readLine();
            }
            // Vérification du mot de passe
            Boolean acces = false;
            while (!acces) {
                pw.println("Veuillez entrer votre mot de passe :");
                pw.println("");
                String mdp = br.readLine();
                if (estLocataire && listeLoc.get(nom).equals(mdp) ||
                        !estLocataire && listeProp.get(nom).equals(mdp)) {
                    pw.println("Connexion réussie");
                    pw.flush();
                    acces = true;
                } else {
                    pw.println("Mot de passe incorrect");
                    pw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return nom;
    }
}
