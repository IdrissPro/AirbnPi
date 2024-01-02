import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PubCrawl {

    public static List<Bar> findOptimalCrawl(Graph graph, Bar startBar) {
        List<Bar> allBars = graph.getBars();
        List<Bar> optimalCrawl = new ArrayList<>();

        // brute force (TSP)
        List<Bar> remainingBars = new ArrayList<>(allBars);
        remainingBars.remove(startBar);  

        List<List<Bar>> permutations = generatePermutations(remainingBars);
        double minDistance = Double.MAX_VALUE;

        for (List<Bar> permutation : permutations) {
            double distance = calculatePathDistance(graph, startBar, permutation);
            if (distance < minDistance) {
                minDistance = distance;
                optimalCrawl = new ArrayList<>(permutation);
            }
        }

        optimalCrawl.add(0, startBar);  
        return optimalCrawl;
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
}

