import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class Serveur {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            //On charge les listes de locataires et de propriétaires
            Identification.setListeLoc(Charger.chargerListeLocataire("listeLocataires.txt"));
            Identification.setListeProp(Charger.chargerListeProprietaire("listeProprietaires.txt"));
            serverSocket = new ServerSocket(2009);
            System.out.println("Le serveur est à l'écoute du port "+serverSocket.getLocalPort());
            while (true) {//On accepte plusieurs clients
                Socket socket = serverSocket.accept();
                System.out.println("Un client s'est connecté");
                ClientHandler clientHandler = new ClientHandler(socket, true);
                // Créer un Thread et démarrer le thread
                Thread thread = new Thread(clientHandler);//On crée un thread pour chaque client
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}


