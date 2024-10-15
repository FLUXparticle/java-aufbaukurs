package _05forkjoinpool;

import java.util.concurrent.*;

import static _05forkjoinpool.WallPainterModel.*;

public class ForkJoinWorker extends RecursiveAction {
	
	private WallPainterModel model;
	
	private Job job;
	
	public ForkJoinWorker(WallPainterModel model, Job job) {
		super();
		this.model = model;
		this.job = job;
	}

	@Override
	protected void compute() {
        model.claim(job);
		int from = job.getFrom();
		int to = job.getTo();
		if (to - from + 1 <= BATCH_SIZE) {
            model.paint(job);
		} else {
            Job[] jobs = model.split(job);
            ForkJoinWorker left = new ForkJoinWorker(model, jobs[0]);
            ForkJoinWorker right = new ForkJoinWorker(model, jobs[1]);
            left.fork();
            right.compute();
            left.join();
		}
	}

}
