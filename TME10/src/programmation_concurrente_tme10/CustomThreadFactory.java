package programmation_concurrente_tme10;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {
	private final ThreadGroup threadGroup;
	
	public CustomThreadFactory(ThreadGroup group) {
		this.threadGroup = group;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(threadGroup, r);
	}

}
