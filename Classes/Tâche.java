import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Tâche {
    
    //Une tâche est un objet avec un statut, un nom et une attribution
    private int statut;
    private String nom;
    private String attribution;

    //On déclare une Tâche avec son nom, son statut et son attribution
    public Tâche(String nom, int statut, String attribution) {
        this.nom = nom;
        this.statut = statut;
        this.attribution = attribution;
    }


    public int getStatut() {
        return statut;
    }

    public String getNom() {
        return nom;
    }

    public String getAttribution() {
        return attribution;
    }

    public String toString() {
        return "Tâche : "+"statut= "+statut+", nom= " + nom +", attribution= " + attribution +" \n";
    }

    public String terminer() {
        if (this.statut == 1) {
            this.statut = 2;
            this.attribution = "";
            //on créer un fichier tampon qui modifie l'état de cette tache 
            //on le renomme ensuite pour remplacer l'ancien fichier
            try {
                BufferedReader brFichier=new BufferedReader(new FileReader("taches.txt"));
                PrintWriter pwFichier=new PrintWriter(new FileWriter("taches_temp.txt"));
                String ligne;
                String[] parts;
                while((ligne=brFichier.readLine())!=null){
                    parts = ligne.split(" ");
                    if(parts[0].equals(this.nom)){
                        pwFichier.println(this.nom+" "+"2");
                    } else {
                        pwFichier.println(ligne);
                    }
                }
                pwFichier.close();
            Charger.copierContenuFichier("taches_temp.txt", "taches.txt");
            }catch(Exception e){
                e.printStackTrace();
            }
            return "La tâche : "+this.nom+" est terminée";
        }
        else {
            return "La tâche "+this.nom+" n'est pas en cours";
        }
    }

    public String commencer(String attribution) {
        if (this.statut == 0) {
            this.statut = 1;
            this.attribution = attribution;
            //on créer un fichier tampon qui modifie l'état de cette tache
            //on le renomme ensuite pour remplacer l'ancien fichier
            try {
                BufferedReader brFichier=new BufferedReader(new FileReader("taches.txt"));
                PrintWriter pwFichier=new PrintWriter(new FileWriter("taches_temp.txt"));
                String ligne;
                String[] parts;
                while((ligne=brFichier.readLine())!=null){
                    parts = ligne.split(" ");
                    if(parts[0].equals(this.nom)){
                        pwFichier.println(this.nom+" "+"1"+" "+attribution);
                    } else {
                        pwFichier.println(ligne);
                    }
                }
                pwFichier.close();
                Charger.copierContenuFichier("taches_temp.txt", "taches.txt");
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            return "La tâche "+this.nom+" vous à été attribuée";
        }
        else if (this.statut == 2) {
            return "La tâche "+this.nom+" n'est pas à faire";
        }
        else {
            return "La tâche "+this.nom+" est déjà en cours";
        }
    }

    public String aFaire() {
        if (this.statut == 2) {
            this.statut = 0;
            this.attribution = "";
            //on créer un fichier tampon qui modifie l'état de cette tache
            //on le renomme ensuite pour remplacer l'ancien fichier
            try {
                BufferedReader brFichier=new BufferedReader(new FileReader("taches.txt"));
                PrintWriter pwFichier=new PrintWriter(new FileWriter("taches_temp.txt"));
                String ligne;
                String[] parts;
                while((ligne=brFichier.readLine())!=null){
                    parts = ligne.split(" ");
                    if(parts[0].equals(this.nom)){
                        pwFichier.println(this.nom+" "+"0");
                    } else {
                        pwFichier.println(ligne);
                    }
                }
                pwFichier.close();
                Charger.copierContenuFichier("taches_temp.txt", "taches.txt");
            }catch(Exception e){e.printStackTrace();}
            return "La tâche "+this.nom+" est à faire";
        }
        else if (this.statut == 1) {
            return "La tâche "+this.nom+" est en cours";
        }
        else {
            return "La tâche "+this.nom+" est déjà à faire";
        }
    }   
}
