import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		try {
		

		if(!g.isNode(i)) {
			return null;
		}
		List<K> allNodesInGraph=g.getNodes();
		PQK<Double, K> mostPop=new PQImp<Double, K>(k);
		List<K> neighborsNodes=g.neighb(i);
		
		allNodesInGraph.findFirst();
		int size=allNodesInGraph.size();
		
		for(int j=0;j<size;j++) {
			if(allNodesInGraph.retrieve().compareTo(i)==0||neighborsNodes.exists(allNodesInGraph.retrieve())) {
				allNodesInGraph.findNext();
			}else {
				double pri=(double)g.deg(allNodesInGraph.retrieve());
				mostPop.enqueue(pri,allNodesInGraph.retrieve());
				allNodesInGraph.findNext();
			}
		}
		
		return mostPop;
		}catch (Exception e) {
			return null;
		}
	}

	// Return the top k recommended friends for user i using common neighbors method. If i does not exist, return null. In case of a tie, users with the lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		try {
			

		if(!g.isNode(i)) {
			return null;
		}
		List<K> allNodesInGraph=g.getNodes();
		PQK<Double, K> CNQueue=new PQImp<Double, K>(k);
		List<K> neighborsNodes=g.neighb(i);
		
		allNodesInGraph.findFirst();
		int size=allNodesInGraph.size();
		
		for(int j=0;j<size;j++) {
			if(allNodesInGraph.retrieve().compareTo(i)==0||neighborsNodes.exists(allNodesInGraph.retrieve())) {
				allNodesInGraph.findNext();
			}else {
				List<K> neighborsI=g.neighb(allNodesInGraph.retrieve());
				neighborsI.findFirst();
				neighborsNodes.findFirst();
				double pri= 0;
				

				//count intersection
				for(int e=0;e<neighborsNodes.size();e++) {
					neighborsI.findFirst();
					for(int e2=0;e2<neighborsI.size();e2++) {
					
						if(neighborsNodes.retrieve().compareTo(neighborsI.retrieve())==0) {
							pri++;
						}
						neighborsI.findNext();
					}
					neighborsNodes.findNext();
				}
				//
				CNQueue.enqueue(pri, allNodesInGraph.retrieve());
				allNodesInGraph.findNext();
			}
			
		}
		
		return CNQueue;
		}catch (Exception e) {
			return null;
		}
	}

	// Read graph from file. The file is a text file where each line contains an edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {

		


			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
