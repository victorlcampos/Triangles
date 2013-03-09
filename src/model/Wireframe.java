package model;

public class Wireframe {
	
	enum WireframeEnum {
		FULL,
		DASHED_ONE,
		DASHED_TWO,
		DASHED_THREE
	}

	int index = 0;
	boolean[] wireframe;
	
	public Wireframe(boolean[] wireframe) {
		this.wireframe = wireframe;
	}
	
	public boolean shouldDraw() {
		boolean should = wireframe[index++];
		index %= wireframe.length;
		return should;
	}
	
	public static Wireframe getDefaultType() {
		boolean[] array = { true };
		return new Wireframe(array);
	}
	
	public static Wireframe getTypeOne() {
		boolean[] array = {
			true, false
		};
		return new Wireframe(array);
	}
	
	public static Wireframe getTypeTwo() {
		boolean[] array = {
			true, false, false, false, false
		};
		return new Wireframe(array);
	}

	public static Wireframe getTypeThree() {
		boolean[] array = {
				true, true, true, true, false, false
			};
			return new Wireframe(array);
	}
	
}