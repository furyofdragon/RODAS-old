package vec3D;

public class Vec3D {
	
	private float x;
	private float y;
	private float z;
	private float length;

	public Vec3D(float x, float y, float z) {
		this.x  = x;
		this.y  = y;
		this.z  = z;
	}
	
	public Vec3D() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
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
	
	public float length() {
		return length = (float) Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vec3D normalize(Vec3D v) {
		float length = v.length;
		Vec3D normalizedV = new Vec3D();
		normalizedV.x = v.x / length;
		normalizedV.y = v.y / length;
		normalizedV.z = v.z / length;
		return normalizedV;
	}
	
	public static Vec3D sum(Vec3D a, Vec3D b) {
		Vec3D result = new Vec3D();
		result.x = a.x + b.x;
		result.y = a.y + b.y;
		result.z = a.z + b.z;
		return result;
	}
	
	public static Vec3D vectorProduce(Vec3D a, Vec3D b) {
		Vec3D result = new Vec3D();
		result.x =    (a.y * b.z - a.z * b.y);
		result.y = -1*(a.x * b.z - a.z * b.x);
		result.z =    (a.x * b.y - a.y * b.x);
		return result;
	}
	
	public static float scalarProduce(Vec3D a, Vec3D b) {
		return a.x*b.x + a.y*b.y +a.z*b.z;
	}
}
