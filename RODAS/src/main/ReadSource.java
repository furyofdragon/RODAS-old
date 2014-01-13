package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class ReadSource {
	
	static int N = 0;			// number of lines in input file
	static int np;				// number of points
	static int nl;				// number of lines
	
	static void ReadDataSource(String strFileName) {
		
		String line = null;
		
		ArrayList<String> al = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(strFileName));
			while ((line = br.readLine()) != null) {
				N = N+1;
				al.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		np = Integer.parseInt(new StringTokenizer(al.get(0), " ").nextToken().trim());
		Vector<Point> vPoints = new Vector<Point>();
		for (int i = 0; i < np; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+1), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			float x = Float.parseFloat(token.nextToken().trim());
			float y = Float.parseFloat(token.nextToken().trim());
			float z = Float.parseFloat(token.nextToken().trim());
			vPoints.add(new Point(id, x, y, z));
		}
		
		
		nl = Integer.parseInt(new StringTokenizer(al.get(np+1), " ").nextToken().trim());
		Vector<Line> vLines = new Vector<Line>();
		for (int i = 0; i < nl; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+1+np+1), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			int p1 = Integer.parseInt(token.nextToken().trim());
			int p2 = Integer.parseInt(token.nextToken().trim());
			vLines.add(new Line(id, p1, p2));
		}
		
	}

}
