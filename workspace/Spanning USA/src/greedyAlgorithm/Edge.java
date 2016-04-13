package greedyAlgorithm;

public class Edge implements Comparable {
	private String start;
	private String end;
	private int distance;

	public Edge(String start, String end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public String getStart() {
		return start;
	}
	
	public String getEnd(){
		return end;
	}

	public int compareTo(Object o) {
		Edge e = (Edge) o;
		if (e.getDistance() == distance) {
			return 0;
		} else if (distance < e.getDistance()) {
			return -1;
		} else {
			return 1;
		}
	}

	public String toString() {
		return start + " " + end + " " + distance;
	}

}
