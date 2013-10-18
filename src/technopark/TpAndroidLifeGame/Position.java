package technopark.TpAndroidLifeGame;

public class Position {
	public int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int hashCode() {
		return  (x << 7) ^ 0b0110001110010 ^ y;
	}

	public boolean equals(Object o) {
		if (o instanceof Position) {
			Position p = (Position) o;
			return p.x == x && p.y == y;
		}
		return false;
	}
}
