
package tme9.question3;

import graphic.Window;
import java.awt.Point;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VonKochMono {
	private final static double LG_MIN = 8.0;
	Window f;

	public VonKochMono(Window f, Point a, Point b, Point c) {
		this.f = f;
		Executor e = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // Depending on the number, the degree of parallelism is incremented. For 1, the behavior is equivalent to the sequential execution. Higher values make the drawing faster.
		e.execute(new Cote(f, b, a, e));
		e.execute(new Cote(f, a, c, e));
		e.execute(new Cote(f, c, b, e));
	}
}