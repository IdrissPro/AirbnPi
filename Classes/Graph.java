import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Graph {
    private List<Bar> bars;
    private Map<Bar, List<Bar>> adjacencyList;
    private Map<Bar, Map<Bar, Integer>> distances;

    public Graph() {
        this.bars = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        this.distances = new HashMap<>();
    }

    public void addBar(Bar bar) {
        bars.add(bar);
        adjacencyList.put(bar, new ArrayList<>());
        distances.put(bar, new HashMap<>());
    }

    public void addConnection(Bar bar1, Bar bar2, int distance) {
        adjacencyList.get(bar1).add(bar2);
        adjacencyList.get(bar2).add(bar1);
        distances.get(bar1).put(bar2, distance);
        distances.get(bar2).put(bar1, distance);
    }

    public List<Bar> getBars() {
        return bars;
    }

    public List<Bar> getNeighbors(Bar bar) {
        return adjacencyList.get(bar);
    }

    public int getDistance(Bar bar1, Bar bar2) {
        return distances.get(bar1).get(bar2);
    }

    public Bar getRandomBar() {
        Random random = new Random();
        int randomIndex = random.nextInt(bars.size());
        return bars.get(randomIndex);
    }

    public int getNumberOfBars() {
        return bars.size();
    }
}

