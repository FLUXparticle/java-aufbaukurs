package _05forkjoinpool;


public class SingleThread extends Thread {
	
	private WallPainterModel model;
	
	private Job job;

	public SingleThread(WallPainterModel model, Job job) {
		super();
		this.model = model;
		this.job = job;
	}

	@Override
	public void run() {
		model.paint(job);
	}

}
