package closestPair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

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
		//pair.load(new File("data/close-pairs-4-in.txt"));
		 pair.load(new File("data/lin105-tsp.txt"));
		pair.sort();
		System.out.println("Resultat: " + pair.closestPair());
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

	public double closestPair() {
		return closestPairRec(sortedByX, sortedByY);
	}

	private double closestPairRec(ArrayList<Point> pointsX, ArrayList<Point> pointsY) {
		HashSet<Point> P = new HashSet<Point>();
		P.addAll(pointsX);
		P.addAll(pointsY);

		if (pointsX.size() < 3) {
			double deltaRoot = Double.MAX_VALUE;
			for (Point s : P) {
				for (Point t : P) {
					double x = Math.abs(s.getX() - t.getX());
					double y = Math.abs(s.getY() - t.getY());
					double deltaX = Math.sqrt((x * x) + (y * y));
					if (deltaX < deltaRoot && !s.equals(t))
						deltaRoot = deltaX;
				}
			}
			return deltaRoot;
		}

		int mid = pointsX.size() / 2;
		ArrayList<Point> Qx = new ArrayList<Point>();
		ArrayList<Point> Qy = new ArrayList<Point>();
		ArrayList<Point> Rx = new ArrayList<Point>();
		ArrayList<Point> Ry = new ArrayList<Point>();
		Qx.addAll(pointsX.subList(0, mid));
		Qy.addAll(pointsY.subList(0, mid));
		Rx.addAll(pointsX.subList(mid, pointsX.size()));
		Ry.addAll(pointsY.subList(mid, pointsY.size()));
		double deltaLeft = closestPairRec(Qx, Qy);
		double deltaRight = closestPairRec(Rx, Ry);

		
		double delta = Math.min(deltaLeft, deltaRight);
		Point m = pointsX.get(mid);
		double L = m.getX();
		ArrayList<Point> Sy = new ArrayList<Point>();
		for (int i = 0; i < pointsX.size(); i++) {
			if (Math.abs(pointsY.get(i).getX() - L) <= delta) {
				Sy.add(pointsY.get(i));
			}
		}
		
		double deltaS = Double.MAX_VALUE;
		for (Point s : Sy) {
			int i = 0;
			while (i < 15 && i < Sy.size()) {
				double x = Math.abs(s.getX() - Sy.get(i).getX());
				double y = Math.abs(s.getY() - Sy.get(i).getY());
				double deltaSPrim = Math.sqrt((x * x) + (y * y));
				if (deltaSPrim < deltaS && !s.equals(Sy.get(i))) {
					deltaS = deltaSPrim;
				}
				i++;
			}
		}
		
		
		if (deltaS < delta) {
			return deltaS;
		} else if (deltaLeft < deltaRight) {
			return deltaLeft;
		} else {
			return deltaRight;
		}

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
			Point p = new Point(x, y);
			sortedByX.add(p);
			sortedByY.add(p);
			row = br.readLine();
		}

		br.close();

	}
}
