package main;

public class Line {
	
	public Line(int id, int p1id, int p2id) {
		super();
		this.id   = id;
		this.p1id = p1id;
		this.p2id = p2id;
	}
	
	int id;
	int p1id;
	int p2id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getP1id() {
		return p1id;
	}
	
	public void setP1id(int p1id) {
		this.p1id = p1id;
	}
	
	public int getP2id() {
		return p2id;
	}
	
	public void setP2id(int p2id) {
		this.p2id = p2id;
	}

}
