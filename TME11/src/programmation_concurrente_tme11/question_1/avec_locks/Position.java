package programmation_concurrente_tme11.question_1.avec_locks;

public enum Position {
	NORD(0), SUD(1);

	private final int index;

	private Position(int index) {
		this.index = index;
	}

	public int index() {
		return index;
	}
	
	public static Position fromInt(int index) {
		if (index == 0) {
			return Position.NORD;
		} else {
			return Position.SUD;
		}
	}
}
