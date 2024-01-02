import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class Locataire implements gestionPaiement{
    //On définit un nom, un appartement et un booléen pour savoir si le loyer a été payé
    private String nom;
    private Appartement appartement;
    private Boolean loyerPaye;
    private BufferedReader br;
    private PrintWriter pw;
    private ArrayList<Boolean> LoyerPayes;

    public Locataire(String nom, Socket socket){
        this.nom = nom;
        this.appartement = null;
        try{
        BufferedReader brFichier=new BufferedReader(new FileReader("loyers.txt"));
        String ligne;
        while((ligne=brFichier.readLine())!=null){
            String[] parts = ligne.split(" ");
            if(parts[0].equals(this.nom)){
                this.loyerPaye = Boolean.parseBoolean(parts[5]);
            }
        }
        this.pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        this.br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e) {}
        }

    public PrintWriter getPr(){
        return this.pw;
    }

    public String getNom(){
        return this.nom;
    }

    public Appartement getAppartement(){
        return this.appartement;
    }

    public Boolean getLoyerPaye(){
        return this.loyerPaye;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setLoyerPaye(Boolean loyerPaye){
        this.loyerPaye = loyerPaye;
    }

    public void setAppartement(Appartement appartement){
        this.appartement = appartement;
    }

    public String toString(){
        return "Locataire : " + this.nom + " Appartement : " + this.appartement.getAdresse() + " Loyer payé : " + this.loyerPaye;
    }

    public void payerLoyer(Socket socket){
       
            setLoyerPaye(true);
            try{
                BufferedReader brFichier=new BufferedReader(new FileReader("loyers.txt"));
                PrintWriter pwFichier=new PrintWriter(new FileWriter("loyers_temp.txt"));
                String ligne;
                String[] parts;
                while((ligne=brFichier.readLine())!=null){
                    parts = ligne.split(" ");
                    if(parts[0].equals(this.nom)){
                        pwFichier.println(this.nom+" true true true true true");//On paye tous les loyers d'un coup
                    } else {
                        pwFichier.println(ligne);//on a recopié le fichier de base avec la nouvelle ligne pour le locataire qui a payé
                    }
                }
                pwFichier.close();
            pw.println("Loyer payé avec succès");
            pw.println(" ");
            Charger.copierContenuFichier("loyers_temp.txt", "loyers.txt"); //Copie du nouveau fichier loyers
            }catch(Exception e){
                e.printStackTrace();
            }
        
    }


    //pour le moment l'historique informe juste du statut du loyer en cours
    public void voirHistoriqueLoyer(Socket socket){
       
        try {
            BufferedReader brFichier = new BufferedReader(new InputStreamReader(new FileInputStream("loyers.txt")));
            String ligne;
            //séparer en 6 élements la ligne du fichier puis vérifier si l'élement 1 correspond au nom du locataire
            while ((ligne = brFichier.readLine()) != null) {
                String[] tab = ligne.split(" ");
                if (tab[0].equals(this.nom)) {
                    pw.println("Vos 5 derniers loyers " + tab[1] + " ," + tab[2] + " ," + tab[3] + " ," + tab[4] + " ," + tab[5]);
                    pw.println(" ");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gererTaches(Socket socket){
        try{
        String tache;
        pw.println("Que voulez-vous faire ? \n 1 - Voir les tâches \n 2 - Modifier les tâches \n 3 - Quitter");
        pw.println("");
        String choix = br.readLine();
        switch (choix) {
            case "1":
                pw.println("Voici les tâches : " + this.appartement.getTaches());
                pw.println(" ");
                break;
            case "2":
                pw.println("Que voulez-vous faire ? \n 1 - Commencer une tâche \n 2 - Terminer une tâche \n 3 - Déclarer une tâche à faire \n 4 - Quitter");
                pw.println("");
                String choix2 = br.readLine();
                switch (choix2) {
                    case "1": //On choisit de s'attribuer une tâche 
                        pw.println("Quelle tâche voulez-vous commencer ?");
                        pw.println("");
                        tache = br.readLine();
                        if (this.appartement.getTache(tache) == null) { //Si la tâche n'existe pas, on peut l'ajouter
                            pw.println("Souhaitez vous ajouter cette tâche ? 1)Oui 2)Non");
                            pw.println("");
                            String choix3 = br.readLine();
                            if (choix3.equals("1")) {
                                this.appartement.ajouterTache(new Tâche(tache, 0, ""));
                                pw.println("Tâche ajoutée");
                                pw.println(this.appartement.getTache(tache).commencer(this.nom));
                            }
                            else {
                                pw.println("Tâche non ajoutée");
                                break;
                            }
                            break;
                        } else {
                            pw.println(this.appartement.getTache(tache).commencer(this.nom));
                        }
                        break;
                    case "2": //On souhaite terminer une tâche peut importe son attribution 
                        pw.println("Quelle tâche voulez-vous terminer ?");
                        pw.println("");
                        tache = br.readLine();
                        while(this.appartement.getTache(tache)==null){ 
                            if (this.appartement.getTache(tache) == null) {
                                pw.println("Tâche inexistante, veuillez entrer une tâche valide");
                                pw.println("");
                                tache = br.readLine();
                            }
                        }
                        pw.println(this.appartement.getTache(tache).terminer());
                        break;

                    case "3"://On déclare qu'une tâche est à faire, ici aussi on peut l'ajouter si elle n'existe pas
                        pw.println("Quelle tâche voulez-vous déclarer à faire ?");
                        pw.println("");
                        tache = br.readLine();
                        if (this.appartement.getTache(tache) == null) {
                            pw.println("Tâche non existante, souhaitez vous ajouter cette tâche ? 1)Oui 2)Non");
                            pw.println("");
                            String choix3 = br.readLine();
                            if (choix3.equals("1")) {
                                this.appartement.ajouterTache(new Tâche(tache, 2, ""));
                                pw.println(this.appartement.getTache(tache).aFaire());
                            }
                            else {
                                pw.println("Tâche non ajoutée");
                                break;
                            }
                            break;
                        } else {
                            pw.println(this.appartement.getTache(tache).aFaire());
                            break;
                        }
                    case "4":
                        pw.println("Au revoir");
                        break;
                    default:
                        pw.println("Choix invalide");
                }
                break;
            case "3":
                pw.println("Au revoir");
                break;
            default:
                pw.println("Choix invalide");
            }
        } catch(IOException e) {}
    }
    
}
