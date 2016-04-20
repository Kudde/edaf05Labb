package closestPair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ClosestPair {
	private HashMap<String, Point> points;
	private ArrayList<Point> sortedByX;
	private ArrayList<Point> sortedByY;

	public ClosestPair() {
		points = new HashMap<String, Point>();
		sortedByX = new ArrayList<Point>();
		sortedByY = new ArrayList<Point>();
	}

	public static void main(String[] args) throws IOException {
		ClosestPair pair = new ClosestPair();
		//pair.load(new File("data/close-pairs-2-in.txt"));
		pair.load(new File("data/lin105-tsp.txt"));
		pair.sort();
	}

	public void sort() {
		Collections.sort(sortedByX, Point.getCompByX());
		Collections.sort(sortedByY, Point.getCompByY());

		System.out.println("Sorted by X");
		for (int i = 0; i < sortedByX.size(); i++) {
			System.out.println(sortedByX.get(i).getX() + "	" + sortedByX.get(i).getY());
		}

		System.out.println("Sorted by Y");
		for (int i = 0; i < sortedByY.size(); i++) {
			System.out.println(sortedByY.get(i).getX() + "	" + sortedByY.get(i).getY());
		}
	}
	
	private double run(ArrayList<Point> pointsX, ArrayList<Point>[] pointsY, int low, int high) {
        int mid = low + (high - low) / 2;
        Point m = pointsX.get(mid);

        double meh = run(pointsX, pointsY, low, mid);
        double meh2 = run(pointsX, pointsY, mid+1, high);
        
        return 0;
    }

	public void load(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String row = br.readLine();
		while (!row.equals("NODE_COORD_SECTION")) {
			row = br.readLine();
		}
		row = br.readLine();
		while (row != null && !row.equals("EOF")) {
			String[] line = row.split(" ");
			String name = line[0];
			Double x = Double.parseDouble(line[1]);
			Double y = Double.parseDouble(line[2]);
			System.out.println(name + "	" + x + "	" + y);
			points.put(name, new Point(x, y));
			sortedByX.add(new Point(x, y));
			sortedByY.add(new Point(x, y));
			row = br.readLine();
		}

		br.close();

	}
}
