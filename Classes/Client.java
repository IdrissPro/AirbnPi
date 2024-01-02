import java.io.*;
import java.util.Scanner;
import java.net.*;


public class Client {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] zero) {//On crée un client qui va se connecter au serveur
        try {
            Socket socket = new Socket("localhost", 2009);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            String message;
            while (true) {
                String message_distant = " ";//On réinitialise message_distant à " " pour pouvoir entrer dans la boucle while
                while(!message_distant.equals("")){//On entre dans la boucle while si message_distant n'est pas vide
                    message_distant = br.readLine();  //Message_distant est le message que le serveur va envoyer et que le client va recevoir
                    System.out.println(message_distant); 
                }//Lorsqu'on atteint une chaine vide, cela signifie que le serveur attend une réponse du client
                message = scan.nextLine(); //Message est le message que le client va envoyer au serveur
                pw.println(message);
            }
        } catch (IOException e) {
            System.err.println("Impossible de créer les flux");
            e.printStackTrace();
        }
    }
    
}