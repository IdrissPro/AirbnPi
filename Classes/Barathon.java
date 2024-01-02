import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Barathon {//Cette classe permet de trouver le chemin optimal pour faire un barathon sur tous les bars en ville

    public static List<Bar> trouverCheminOptimal(Graph graph, Bar startBar) {
        List<Bar> allBars = graph.getBars();
        List<Bar> cheminOptimal = new ArrayList<>();

        // brute force (TSP)
        List<Bar> remainingBars = new ArrayList<>(allBars);
        remainingBars.remove(startBar);  

        List<List<Bar>> permutations = generatePermutations(remainingBars);
        double minDistance = Double.MAX_VALUE;

        for (List<Bar> permutation : permutations) {
            double distance = calculatePathDistance(graph, startBar, permutation);
            if (distance < minDistance) {
                minDistance = distance;
                cheminOptimal = new ArrayList<>(permutation);
            }
        }

        cheminOptimal.add(0, startBar);  
        return cheminOptimal;
    }

    private static List<List<Bar>> generatePermutations(List<Bar> bars) {
        List<List<Bar>> permutations = new ArrayList<>();
        generatePermutationsHelper(bars.size(), bars, permutations);
        return permutations;
    }

    private static void generatePermutationsHelper(int n, List<Bar> bars, List<List<Bar>> result) {
        if (n == 1) {
            result.add(new ArrayList<>(bars));
        } else {
            for (int i = 0; i < n - 1; i++) {
                generatePermutationsHelper(n - 1, bars, result);
                if (n % 2 == 0) {
                    Collections.swap(bars, i, n - 1);
                } else {
                    Collections.swap(bars, 0, n - 1);
                }
            }
            generatePermutationsHelper(n - 1, bars, result);
        }
    }

    private static double calculatePathDistance(Graph graph, Bar startBar, List<Bar> path) {
        double distance = graph.getDistance(startBar, path.get(0));
        for (int i = 0; i < path.size() - 1; i++) {
            distance += graph.getDistance(path.get(i), path.get(i + 1));
        }
        return distance;
    }

    public static Graph createGraph() {
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

    public static Bar findBarByName(Graph graph, String name) {
        for (Bar bar : graph.getBars()) {
            if (bar.getAdresse().equalsIgnoreCase(name)) {
                return bar;
            }
        }
        
        return null;
    }
}

