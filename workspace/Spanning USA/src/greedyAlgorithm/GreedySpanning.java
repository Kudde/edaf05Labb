package greedyAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GreedySpanning {
	private PriorityQueue<Edge> edges;
	private HashSet<String> nodes;

	public GreedySpanning(){
		edges = new PriorityQueue<Edge>();
		nodes = new HashSet<String>();
	}
	
	public static void main(String[] args) throws IOException{
		GreedySpanning gs = new GreedySpanning();
		gs.load(new File("USA-highway-miles.txt"));
		gs.run();
	}
	
	public void run(){
		int distance = 0;
		UnionFind uf = new UnionFind(nodes);
		Edge edge = edges.poll();
		
		while(!edges.isEmpty()){
			String u = edge.getStart();
			String v = edge.getEnd();
			if(!uf.find(u).equals(uf.find(v))){
				uf.union(uf.find(u), uf.find(v));
				distance += edge.getDistance();
				System.out.println(u + " " + v + " " + edge.getDistance());
			}
			edge = edges.poll();
			
		}
		System.out.println("Distance: " + distance);
	}
	
	public void load(File file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String row = br.readLine();
		//Läser in städer
		while(!row.contains("--")){
			if(row.startsWith("\"")){
				row = row.substring(1, row.length()-2);
			}else{
				row = row.substring(0, row.length()-1);
			}
			System.out.println(row);
			nodes.add(row);
			row = br.readLine();
		}
		
		//Läser in kanter
		while(row != null){
			String[] line = row.split("--");
			String start = line[0];
			if(start.startsWith("\"")){
				start = start.substring(1, start.length()-1);
			}
			String[] lineEnd = line[1].split("\\[");
			String end = lineEnd[0].substring(0, lineEnd[0].length()-1);
			if(end.startsWith("\"")){
				end = end.substring(1, end.length()-1);
			}
			String distance = lineEnd[1].substring(0, lineEnd[1].length()-1);
			System.out.println(start + " -- " + end + " " + distance);
			
			
			edges.add(new Edge(start,end,Integer.parseInt(distance)));
			row = br.readLine();
		}
		br.close();
	}
}
