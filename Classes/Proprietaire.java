import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;

public class Proprietaire implements gestionPaiement{

    //On définit le nom et l'unique appartement du propriétaire
    private String nom;
    private Appartement appartement;
    private PrintWriter pw;
    private BufferedReader br;

    public Proprietaire(String nom){
        this.nom = nom;
        this.appartement = null;
    }

    public String getNom(){
        return this.nom;
    }

    public Appartement getAppartement(){
        return this.appartement;
    }

    public void setAppartement(Appartement appartement){
        this.appartement = appartement;
    }

    public void voirHistoriqueLoyer(Socket socket){
        //on parcours la liste des locataires, si ceux ci sont dans 'appartement' alors on affiche leur nom et si ils ont payé le loyer
        try{
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader brFichier=new BufferedReader(new FileReader("loyers.txt"));
            String ligne;
            while((ligne=brFichier.readLine())!=null){
                //on sépare les lignes en 6 parties
                String[] parts = ligne.split(" ");
                pw.println("Locataire : " + parts[0] + "-> Janvier : " + parts[1] + " Février : " + parts[2] + " Mars : " + parts[3] + " Avril : " + parts[4] + " Mai : " + parts[5]+"\n ");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*public void sauvegarderMessage(String message,String nomDestinataire)
    {
        try{
        //Les fichiers locataires devront avoir la syntaxe Nom_role.txt (role=Locataire ou Proprietaire)
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nomDestinataire + "_locataire.txt", true))); //true pour ouvrir le fichier en mode ajout
        // Ajouter le nouveau message au fichier du locataire
        writer.println(message); 
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/  // Cette méthode n'est pas utilisée, elle servirait à sauvegarder les messages envoyés par le propriétaire aux locataires

    public void envoyerRappel(Socket socket) {//envoie un message à un locataire pour lui rappeler de payer son(es) loyer(s) (de retard)
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter locPw;//Le printwriter associé au locataire visé par le rappel
            boolean locataireValide = false; //true si le locataire est connecté
                pw.println("A qui souhaitez-vous envoyer un rappel ?");
                pw.println("");
                String locVisé = br.readLine();
                for (Locataire locataire : appartement.getListeLocataires()) {
                    if (locataire.getNom().equals(locVisé)) {
                        locPw = locataire.getPr();
                            if (locataire.getLoyerPaye()==false){//Si un locataire a payé son dernier loyer, il a forcément payer les autres
                                locPw.println("Vous avez du retard dans votre paiement");
                            } else {
                                pw.println("Le locataire a payé son loyer");
                            }
                            locataireValide = true;
                            break; 
                    }
                }
                if (!locataireValide) {
                    pw.println("Le locataire n'est pas connecté... !");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
