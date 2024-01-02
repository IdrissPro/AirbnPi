import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

class ClientHandler implements Runnable {

	    private Socket socket;
		private BufferedReader br;
		private PrintWriter pw;
		private Proprietaire proprietaire;//instancié seulement si le client est un propriétaire
		private Locataire locataire;//instancié seulement si le client est un locataire
		private String nom;
		private int clientId;//indique au client si d'autres client sont connectés
		private Boolean estLocataire;//indique si le client est un locataire ou un propriétaire
		private Appartement appartement;
		private Boolean fin=false;
		private static int count = 1;  // On le met en static pour que le compteur soit partagé dans toutes les instances de classe

		ClientHandler(Socket socket,Boolean estLocataire) {
			try{
			this.socket=socket;
			this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.estLocataire=estLocataire;
			this.clientId = count++;
			} catch(IOException e) {}
		}

		public boolean definirRole(){//permet au client de choisir son statut
			
			try { 
				boolean role;
				String choix;
				pw.println("Qui est tu? 1)Locataire 2)Propriétaire");
				pw.println("");
				choix=br.readLine();
				while(!(choix.equals("1")) && !(choix.equals("2")))
				{
					pw.println("Choix invalide. Veuillez choisir une option valide.");
					pw.println("");
					choix=br.readLine();
				}
				role=(choix.equals("1"));
				return(role);
			} catch(IOException e) {}
			return false;
		}
	

		public int getClientId() {
			return clientId;
		}

		public Boolean affichageProprietaire(Proprietaire proprietaire) {
			Boolean fin=false;
			String choix;
			try{
				
				pw.println("Que voulez-vous faire ? \n 1 - Voir l'historique des loyers payés \n 2 - Envoyer un rappel \n 3 - Quitter");
				pw.println("");
				choix = br.readLine();
				switch (choix) {
					case "1":
						proprietaire.voirHistoriqueLoyer(socket);
						break;
			
					case "2":
						proprietaire.envoyerRappel(socket);
						break;
					case "3":
						pw.println("Au revoir");
						fin=true;
						break;

					default:
						pw.println("Choix invalide");
				}
				
			} catch(IOException e) {}
			return fin;
		}
		

		public Boolean affichageLocataire(Locataire locataire) {
			Boolean fin=false;
			try{
				String choix;
				pw.println("Que voulez-vous faire ? \n 1 - Payer le loyer \n 2 - Voir l'historique des loyers payés \n 3 - Gérer les tâches \n 4 - Organiser un barathon \n 5 - Quitter");
				pw.println("");
				choix = br.readLine();
				switch (choix) {
				case "1":
					locataire.payerLoyer(socket);
					break;
		
				case "2":
					locataire.voirHistoriqueLoyer(socket);
					break;
		
				case "3":
					locataire.gererTaches(socket);
					break;
				case "4":
					GestionBar.menuBarathon(socket);
					break;
		
				case "5":
					pw.println("Au revoir");
					fin=true;
					break;
				default:
					pw.println("Choix invalide");
			}
			} catch(IOException e) {}
			return fin;
		}
		
			
	    public void run() {
			pw.println("Votre numéro de client est : "+clientId+"");
			this.estLocataire=definirRole();
			this.nom=Identification.identification(estLocataire,socket);
			//on va créer avec le nom du client et son statut un locataire ou un propriétaire
			if(estLocataire)
			{
				this.locataire=new Locataire(nom, socket); 
				this.appartement=AppartementPartage.getAppartementPartage();//L'appartement partagé est le même pour tous les locataires
				AppartementPartage.setAppartementPartage(appartement);//On met à jour l'appartement partagé
				this.locataire.setAppartement(appartement);//On met à jour l'appartement du locataire
				this.appartement.addLocataire(locataire);//On ajoute le locataire à la liste des locataires de l'appartement
			}
			else
			{
				this.proprietaire=new Proprietaire(nom);
				this.appartement=AppartementPartage.getAppartementPartage();
				AppartementPartage.setAppartementPartage(appartement);
				this.proprietaire.setAppartement(appartement);
			}
			while(fin==false)//On affiche le menu tant que le client ne quitte pas
			{
				if(estLocataire)
				{
					fin=affichageLocataire(locataire);//on retourne true si le client veut quitter
				}
				else
				{
					fin=affichageProprietaire(proprietaire);
				}
			}
			count--;
	    }
}