import java.util.*;

public class Dijkstra {

    public static List<Bar> findShortestPath(Graph graph, Bar startBar, Bar endBar) {
        Map<Bar, Integer> distanceMap = new HashMap<>();
        Map<Bar, Bar> previousNodeMap = new HashMap<>();
        PriorityQueue<Bar> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distanceMap::get));

        for (Bar bar : graph.getBars()) {
            distanceMap.put(bar, Integer.MAX_VALUE);
            previousNodeMap.put(bar, null);
        }

        distanceMap.put(startBar, 0);
        priorityQueue.add(startBar);

        while (!priorityQueue.isEmpty()) {
            Bar currentBar = priorityQueue.poll();

            if (currentBar.equals(endBar)) {
                return constructPath(previousNodeMap, endBar);
            }

            for (Bar neighbor : graph.getNeighbors(currentBar)) {
                int newDistance = distanceMap.get(currentBar) + graph.getDistance(currentBar, neighbor);

                if (newDistance < distanceMap.get(neighbor)) {
                    distanceMap.put(neighbor, newDistance);
                    previousNodeMap.put(neighbor, currentBar);
                    priorityQueue.add(neighbor);
                }
            }
        }

        // Pas de chemin
        return new ArrayList<>();
    }

    private static List<Bar> constructPath(Map<Bar, Bar> previousNodeMap, Bar endBar) {
        List<Bar> path = new ArrayList<>();
        Bar currentBar = endBar;

        while (currentBar != null) {
            path.add(currentBar);
            currentBar = previousNodeMap.get(currentBar);
        }

        Collections.reverse(path);
        return path;
    }
}
