import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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



		public static Graph createGraphFromTxtFile(String filePath) {
		  Graph graph = new Graph();

		  try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
		      String line;
		      boolean readingBars = false;

		      while ((line = reader.readLine()) != null) {
		          if (line.startsWith("# Connections")) {
		              readingBars = false;
		              continue;  // Skip the line with the header
		          } else if (line.startsWith("# Bars")) {
		              readingBars = true;
		              continue;  // Skip the line with the header
		          }

		          String[] parts = line.split(",");
		          Bar bar = new Bar(parts[0].trim());
		          
		          if (readingBars) {
		              graph.addBar(bar);
		          } else {
		              Bar fromBar = findBarByName(graph.getBars(), parts[0].trim());
		              Bar toBar = findBarByName(graph.getBars(), parts[1].trim());
		              int distance = Integer.parseInt(parts[2].trim());

		              if (fromBar != null && toBar != null) {
		                  graph.addConnection(fromBar, toBar, distance);
		              }
		          }
		      }
		  } catch (IOException e) {
		      e.printStackTrace();
		  }

		  return graph;
	}


    public static Graph createGraph() {
        return createGraphFromTxtFile("graph.txt");
    }



    public static List<Bar> findBarsByName(Graph graph, String[] names) {
        List<Bar> bars = graph.getBars();
        List<Bar> result = new ArrayList<>();

        for (String name : names) {
            Bar bar = findBarByName(bars, name.trim());
            if (bar != null) {
                result.add(bar);
            }
        }

        return result;
    }

    public static Bar findBarByName(List<Bar> bars, String name) {
        for (Bar bar : bars) {
            if (bar.getAdresse().equalsIgnoreCase(name)) {
                return bar;
            }
        }
        return null;
    }
}


