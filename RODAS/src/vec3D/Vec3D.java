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
	
	public Vec3D normalize(Vec3D normalizedVector) {
		float length = normalizedVector.length;
		x = x / length;
		y = y / length;
		z = z / length;
		normalizedVector.x = x;
		normalizedVector.y = y;
		normalizedVector.z = z;
		return normalizedVector;
	}
	
	@SuppressWarnings("null")
	public Vec3D vectorProduce(Vec3D a, Vec3D b) {
		Vec3D result = null;
		result.x =    (a.y * b.z - a.z * b.y);
		result.y = -1*(a.x * b.z - a.z * b.x);
		result.z =    (a.x * b.y - a.y * b.x);
		return result;
	}
	
	public float scalarProduce(Vec3D a, Vec3D b) {
		return a.x*b.x + a.y*b.y +a.z*b.z;
	}
}
