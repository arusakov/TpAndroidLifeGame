package technopark.TpAndroidLifeGame;

import java.util.*;

public class LifeModel {
	private int rows, cols;
	private Set<Position> field;

	private int step;
	private int dots;
	private boolean cycle;

	private static final int transforms[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

	public LifeModel(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		step = 0;
		cycle = true;

		field = new HashSet<>();
	}

	public void init(int count) {
		Random rnd = new Random();
		Position p;
		this.dots = count;

		while (count-- != 0) {
			do {
				p = new Position(rnd.nextInt(cols), rnd.nextInt(rows));
			}
			while (field.contains(p));
			field.add(p);
		}
	}

	public int getStep() {
		return step;
	}

	public void setWorld(boolean cycle) {
		this.cycle = cycle;
	}

	public void update(int x, int y) {
		if (inField(x, y)) {
			Position p = new Position(x, y);
			if (field.contains(p)) {
				field.remove(p);
				--dots;
			}
			else {
				field.add(p);
				++dots;
			}
		}
	}

	public void next() {
		if (dots == 0) {
			return;
		}

		Map<Position, Integer> fieldWeights = new HashMap<>();
		Position newPos;
		Integer weight;

		for (Position p : field) {
			for (int[] t : transforms) {
				newPos = transformPosition(p, t);
				if (newPos == null) {
					continue;
				}

				if (fieldWeights.containsKey(newPos)) {
					weight = fieldWeights.get(newPos);
					++weight;
				}
				else {
					fieldWeights.put(newPos, 1);
				}
			}
		}

		Set<Position> newField = new HashSet<>();
		dots = 0;
		for (Map.Entry<Position, Integer> entry : fieldWeights.entrySet()) {
			weight = entry.getValue();
			if (weight < 2 || weight > 3) {
				continue;
			}

			newPos = entry.getKey();
			if (weight == 3 || field.contains(newPos)) {
				newField.add(newPos);
				++dots;
			}
		}
		field = newField;

		++step;
	}

	public void clear() {
		dots = 0;
		step = 0;

		field.clear();
	}

	private Position transformPosition(Position p, int[] transforms) {
		int x = p.x + transforms[0],
				y = p.y + transforms[1];

		if (cycle) {
			x = x < 0 ? cols + x : x % cols;
			y = y < 0 ? rows + y : y % rows;
		}
		else if (!inField(x, y)) {
			return null;
		}
		return new Position(x, y);
	}

	private boolean inField(int x, int y) {
		if (x < 0 || x >= cols || y < 0 || y >= rows) {
			return false;
		}
		return true;
	}
}
