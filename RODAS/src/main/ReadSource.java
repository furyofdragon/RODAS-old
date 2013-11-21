package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadSource {
	
	static int N = 0;			// number of lines in input file
	static int np;				// number of points
	static int nl;				// number of lines
	
	static float points[][];	// Array of points: id, x, y, z
	static float lines[][];		// Array of lines: id, start_point_id, end_point_id
	
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
		
		points = new float[np][4];
		for (int i = 0; i < np; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i), " ");
			points[i][0] = Integer.parseInt(token.nextToken().trim());
			points[i][1] = Float.parseFloat(token.nextToken().trim());
			points[i][2] = Float.parseFloat(token.nextToken().trim());
			points[i][3] = Float.parseFloat(token.nextToken().trim());
			
		}
		
		
		nl = Integer.parseInt(new StringTokenizer(al.get(np), " ").nextToken().trim());
		
		lines = new float[nl][3];
		for (int i = 0; i < nl; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i), " ");
			lines[i][0] = Integer.parseInt(token.nextToken().trim());
			lines[i][1] = Integer.parseInt(token.nextToken().trim());
			lines[i][2] = Integer.parseInt(token.nextToken().trim());
			
		}
		
	}
	
	static int getPointsArrayDimension() {
		return points.length;
	}
	
	static float[][] getPointsDataArray() {
		return points;
	}
	
	static int getLinesArrayDimension() {
		return lines.length;
	}
	
	static float[][] getlinesDataArray() {
		return lines;
	}

}
