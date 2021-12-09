package programmation_concurrente_tme6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SegTournant implements Runnable {
	// Color codes for formatting the output. Not necessary
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	
	
	private final Lock l = new ReentrantLock();
	private final Condition segCalled = l.newCondition();
	private final Condition segFree = l.newCondition();
	private final Condition entered = l.newCondition();
	private final Condition exited = l.newCondition();
	private final Condition positionOK = l.newCondition();
	private boolean occupied = false;
	private boolean called = false;
	private boolean posOK = true;
	private int pDest;
	private final PoolHangars poolH;
	
	public SegTournant(PoolHangars p) {
		poolH = p;
	}

	public void run() {
		try {
			while (true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				pDest = poolH.chercherDispo();
				seDeplacer();
				attendreVide();
			}
		} catch (InterruptedException e) {
			System.out.println("Terminaison sur interruption du segment tournant");
		}
	}

	private void attendreAppel() throws InterruptedException {
		l.lock();
		try {
			while (!called) {
				segCalled.await();
			}
			System.out.println("[Rotating segment]: CALLED");
		} finally {
			l.unlock();
		}
	}

	private void seDeplacer() throws InterruptedException {
		l.lock();
		try {
			System.out.println("[Rotating segment]: MOVING");
		} finally {
			l.unlock();
		}
		Thread.sleep(1000);
		l.lock();
		try {
			System.out.println("[Rotating segment]: STOPPED");
			posOK = true;
			positionOK.signalAll();
		} finally {
			l.unlock();
		}
	}

	private void attendreEntree() throws InterruptedException {
		l.lock();
		try {
			while (!occupied) {
				entered.await();
			}
		} finally {
			l.unlock();
		}
	}

	private void attendreVide() throws InterruptedException {
		l.lock();
		try {
			while (occupied) {
				exited.await();
			}
		} finally {
			l.unlock();
		}
	}

	public void appeler(int id) throws InterruptedException {
		l.lock();
		try {
			while (called) {
				segFree.await();
			}
			pDest = id;
			called = true;
			posOK = false;
			segCalled.signalAll();
		} finally {
			l.unlock();
		}
	}

	public void attendrePositionOK() throws InterruptedException {
		l.lock();
		try {
			while (!posOK) {
				positionOK.await();
			}
		} finally {
			l.unlock();
		}
	}
	
	public void entrer(int id) {
		l.lock();
		try {
			System.out.println("[TRAIN " + id + "]: ENTERING Rotating segment");
			occupied = true;
			posOK = false;
			entered.signalAll();
		} finally {
			l.unlock();
		}
	}
	
	public int getPosition() {
		return pDest;
	}
	
	public void sortir(int id) {
		l.lock();
		try {
			System.out.println("[TRAIN " + id + "]: EXITING Rotating segment");
			occupied = false;
			called = false;
			exited.signalAll();
			segFree.signalAll();
		} finally {
			l.unlock();
		}
	}
}
