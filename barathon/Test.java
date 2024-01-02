import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        
        Graph graph = createGraph();

        // Input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the starting point (or press Enter for a random starting point): ");
        String startInput = scanner.nextLine().trim();

        
        Bar startBar = (startInput.isEmpty()) ? null : findBarByName(graph, startInput);

        // Trouver le chemin optimal
        List<Bar> optimalCrawl = PubCrawl.findOptimalCrawl(graph, startBar);

        // Afficher le resultat
        System.out.println("Optimal Pub Crawl:");
        for (Bar bar : optimalCrawl) {
            System.out.println(bar.getAdresse());
        }
    }

		private static Graph createGraph() {
        Graph graph = new Graph();

        // Create bars
        Bar bar1 = new Bar("Bar 1");
        Bar bar2 = new Bar("Bar 2");
        Bar bar3 = new Bar("Bar 3");
        Bar bar4 = new Bar("Bar 4");

        // Add bars to the graph
        graph.addBar(bar1);
        graph.addBar(bar2);
        graph.addBar(bar3);
        graph.addBar(bar4);

        // Add connections between bars with distances
        
        //test 1 
        // Ça marche pas pour cet exemple car graphe non complet
        /* graph.addConnection(bar1, bar2, 5);
        graph.addConnection(bar2, bar3, 5);
        graph.addConnection(bar3, bar4, 5); */
        
        //test 2
        graph.addConnection(bar1, bar2, 1);
        graph.addConnection(bar1, bar3, 3);
        graph.addConnection(bar1, bar4, 4);
        graph.addConnection(bar2, bar3, 4);
        graph.addConnection(bar2, bar4, 2);
        graph.addConnection(bar3, bar4, 3); 
        
        // test 3
        /* graph.addConnection(bar1, bar3, 8);
        graph.addConnection(bar1, bar4, 10);
        graph.addConnection(bar2, bar3, 2);
        graph.addConnection(bar2, bar4, 4);
        graph.addConnection(bar3, bar4, 6);*/

        return graph;
    }

    private static Bar findBarByName(Graph graph, String name) {
        for (Bar bar : graph.getBars()) {
            if (bar.getAdresse().equalsIgnoreCase(name)) {
                return bar;
            }
        }
        
        return null;
    }
}

