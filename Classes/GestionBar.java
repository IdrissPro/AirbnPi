import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GestionBar {

    public static void menuBarathon(Socket socket) {
        try{
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        pw.println("Bienvenue dans le gestionnaire de barathon");
        pw.println("Que souhaitez-vous faire ? \n 1 - Obtenir le chemin optimale pour visiter tous les bars \n 2 - Créer votre barathon optimisé \n 3 - Quitter");
        pw.println("");
        String choix = br.readLine();
        while (!(choix.equals("1")) && !(choix.equals("2")) && !(choix.equals("3"))) {
            pw.println("Choix invalide. Veuillez choisir une option valide.");
            pw.println("");
            choix = br.readLine();
        }
        switch (choix) {
            case "1":
                barathonOptimal(socket);
                break;
            case "2":
                creerBarathon(socket);
                break;
            default:
                break;
        }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

        public static void creerBarathon(Socket socket) {
            try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Graph graph = Dijkstra.createGraph();

            //Scanner scanner = new Scanner(System.in);
            pw.println("Entrer la liste des bars à visiter (séparés d'une virgule): ");
            pw.println("");
            String input = br.readLine().trim();
            String[] barNames = input.split(",");
            List<Bar> orderedBars = Dijkstra.findBarsByName(graph, barNames);

            List<Bar> optimalPath = OrderedPathFinder.findOptimalPath(graph, orderedBars);

            pw.println("Le chemin optimale est :");
            for (Bar bar : optimalPath) {
                pw.println(bar.getAdresse());
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }

        public static void barathonOptimal(Socket socket) {
            try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Graph graph = Barathon.createGraph();

            pw.println("Entrer le point de départ :");
            pw.println("");
            String startInput = br.readLine().trim();

            Bar startBar = (startInput.isEmpty()) ? null : Barathon.findBarByName(graph, startInput);

            // Trouver le chemin optimal
            List<Bar> optimalCrawl = Barathon.trouverCheminOptimal(graph, startBar);

            // Afficher le resultat
            pw.println("Le barathon optimisé est :");
            for (Bar bar : optimalCrawl) {
                pw.println(bar.getAdresse());
            }
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        
        
    }

}


