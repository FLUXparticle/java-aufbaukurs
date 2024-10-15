package _05forkjoinpool;


public class ThreadPoolWorker implements Runnable {

	private WallPainterModel model;
	
	private Job job;

	public ThreadPoolWorker(WallPainterModel model, Job job) {
		super();
		this.model = model;
		this.job = job;
	}

	@Override
	public void run() {
		model.paint(job);
	}

}
