import java.util.ArrayList;
import java.util.List;

public class OrderedPathFinder {

    public static List<Bar> findOptimalPath(Graph graph, List<Bar> orderedList) {
        List<Bar> optimalPath = new ArrayList<>();

        for (int i = 0; i < orderedList.size() - 1; i++) {
            Bar startBar = orderedList.get(i);
            Bar endBar = orderedList.get(i + 1);
            List<Bar> subPath = Dijkstra.findShortestPath(graph, startBar, endBar);
            optimalPath.addAll(subPath.subList(0, subPath.size() - 1));  // Exclure le dernier bar pour éviter la duplication
        }

        // Ajouter le dernier bar de la liste ordonnée
        optimalPath.add(orderedList.get(orderedList.size() - 1));

        return optimalPath;
    }
}

