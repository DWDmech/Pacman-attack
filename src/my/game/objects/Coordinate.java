package my.game.objects;

public class Coordinate {
	private int X;
	private int Y;

	public Coordinate(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	public void setXY(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		
		if (o == null || getClass() != o.getClass())
			return false;

		Coordinate that = (Coordinate) o;

		if (X != that.X)
			return false;
		return Y == that.Y;

	}

	@Override
	public int hashCode() {
		int result = X;
		result = 31 * result + Y;
		return result;
	}

	@Override
	public String toString() {
		return "[X=" + X + ", Y=" + Y + "]";
	}

	
}
