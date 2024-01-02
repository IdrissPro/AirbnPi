import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Test2 {

    public static void main(String[] args) {
        
        Graph graph = createGraph();

        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ordered list of bars (comma-separated): ");
        String input = scanner.nextLine().trim();
        String[] barNames = input.split(",");
        List<Bar> orderedBars = findBarsByName(graph, barNames);

        
        List<Bar> optimalPath = OrderedPathFinder.findOptimalPath(graph, orderedBars);

       
        System.out.println("Optimal Path:");
        for (Bar bar : optimalPath) {
            System.out.println(bar.getAdresse());
        }
    }

	private static Graph createGraph() {
	    Graph graph = new Graph();

	    Bar bar1 = new Bar("Bar 1");
	    Bar bar2 = new Bar("Bar 2");
	    Bar bar3 = new Bar("Bar 3");
	    Bar bar4 = new Bar("Bar 4");
	    Bar bar5 = new Bar("Bar 5");
	    Bar bar6 = new Bar("Bar 6");
	    Bar bar7 = new Bar("Bar 7");
	    Bar bar8 = new Bar("Bar 8");

	    graph.addBar(bar1);
	    graph.addBar(bar2);
	    graph.addBar(bar3);
	    graph.addBar(bar4);
	    graph.addBar(bar5);
	    /**/
			graph.addBar(bar6);
			graph.addBar(bar7);
			graph.addBar(bar8);

			//test 1
	    /* graph.addConnection(bar1, bar2, 5);
	    graph.addConnection(bar2, bar3, 5);
	    graph.addConnection(bar3, bar4, 20);
	    graph.addConnection(bar3, bar5, 5);
	    graph.addConnection(bar5, bar4, 5); */
	    
	    
	    //test 2
	    graph.addConnection(bar1, bar2, 2);
	    graph.addConnection(bar1, bar3, 4);
	    graph.addConnection(bar1, bar4, 3);
	    graph.addConnection(bar1, bar7, 2);
	    
	    graph.addConnection(bar2, bar3, 5);
	    graph.addConnection(bar2, bar5, 7);
	    
	    graph.addConnection(bar3, bar4, 8);
	    graph.addConnection(bar3, bar5, 6);
	    graph.addConnection(bar3, bar7, 7);
	    
	    graph.addConnection(bar4, bar7, 6);
	    
	    graph.addConnection(bar5, bar6, 5);
	    graph.addConnection(bar5, bar7, 1);
	    
	    graph.addConnection(bar6, bar8, 3);
	    
	    graph.addConnection(bar7, bar8, 2);
	    
	    

	    return graph;
	}


    private static List<Bar> findBarsByName(Graph graph, String[] names) {
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

    private static Bar findBarByName(List<Bar> bars, String name) {
        for (Bar bar : bars) {
            if (bar.getAdresse().equalsIgnoreCase(name)) {
                return bar;
            }
        }
        return null;
    }
}

