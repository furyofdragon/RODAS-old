package main;

public class Point {

	public Point(int id, float x, float y, float z) {
		super();
		this.id = id;
		this.x  = x;
		this.y  = y;
		this.z  = z;
	}

	int   id;
	float x;
	float y;
	float z;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
}
