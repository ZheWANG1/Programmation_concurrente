package tme9.question3;
import graphic.Window;

import java.awt.*;
import java.util.concurrent.Executor;

public class Cote implements Runnable {
	private final static double LG_MIN = 8.0;
	private final Window f;
	private final Point m, n;
	private final Executor e;

	public Cote (Window f, Point m, Point n, Executor e) {
		this.f = f;
		this.m = m;
		this.n = n;
		this.e = e;
	}	
		
	public void run () {	
		final double xa, ya, xb, yb, xc, yc;
			
		if ( Segment.longueur(m, n) > LG_MIN ) {
			xa = (2 * m.x + n.x) / 3.0;
			xc = (m.x + 2 * n.x) / 3.0;
			ya = (2 * m.y + n.y) / 3.0;
			yc = (m.y + 2 * n.y) / 3.0;
			xb = xa + ( xc - xa - (Math.sqrt(3.0) * (yc - ya)) ) / 2.0;
			yb = ya + ( yc - ya + (Math.sqrt(3.0) * (xc - xa)) ) / 2.0;
			
			Point a = new Point();
			a.setLocation(xa, ya);
			Point b = new Point();
			b.setLocation(xb, yb);
			Point c = new Point();
			c.setLocation(xc, yc);
			
			e.execute(new Cote(f, m, a, e));
			e.execute(new Cote(f, a, b, e));
			e.execute(new Cote(f, b, c, e));
			e.execute(new Cote(f, c, n, e));
		}
		else {
			Segment.tracer(f, m, n);
		}
	}
}