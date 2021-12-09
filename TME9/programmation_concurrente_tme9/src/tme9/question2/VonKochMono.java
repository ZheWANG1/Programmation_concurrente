package tme9.question2;

import graphic.Window;
import java.awt.Point;

public class VonKochMono {
	private final static double LG_MIN = 8.0;
	Window f;

	public VonKochMono(Window f, Point a, Point b, Point c) {
		this.f = f;
		new Thread(new Cote(f, b, a)).start();
		new Thread(new Cote(f, a, c)).start();
		new Thread(new Cote(f, c, b)).start();
	}
}