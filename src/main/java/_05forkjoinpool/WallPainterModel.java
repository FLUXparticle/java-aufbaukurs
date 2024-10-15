package _05forkjoinpool;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class WallPainterModel {
	
	private static final int MAX_TIME = 100;
	
	private static final int TIME_STEP = 5;

    static final int CNT_SHELVES = 64;

    static final int BATCH_SIZE = CNT_SHELVES / 16;

    static final int HEIGHT = 100;

    public class Worker {
		
		private int color;
		
		private double pos;
		
		public Worker(int color) {
			this.color = color;
			pos = -0.5; // (CNT_SHELVES - 1) / 2.0;
		}
		
		public int getColor() {
			return color;
		}

		public double getPos() {
			return pos;
		}

		private void goTo(final double newPos) {
			final double oldPos = pos;
			final double maxTime = Math.abs(newPos - oldPos) * MAX_TIME;
			if (maxTime > 0) {
				for (int time = 0; time <= maxTime; time += TIME_STEP) {
					double t = (double) time / maxTime;
					pos = oldPos * (1.0 - t) + newPos * t;
					listener.onUpdate();
					sleep(TIME_STEP);
				}
			}
			pos = newPos;
			listener.onUpdate();
		}

	}
	
	private final AtomicBoolean waiting = new AtomicBoolean();
	
	public void pauseResume() {
		if (waiting.compareAndSet(true, false)) {
			synchronized (waiting) {
				waiting.notifyAll();
			}
		} else {
			waiting.set(true);
		}
	}
	
	private Collection<Job> jobs;
	
	private Map<Integer, Worker> workers; 
	
	private int[] shelves;
	
	private UpdateListener listener;
	
	public WallPainterModel() {
		jobs = new ConcurrentLinkedQueue<>();
		workers = new ConcurrentHashMap<>();
		shelves = new int[CNT_SHELVES];
	}
	
	public Job newJob() {
		jobs.clear();
		workers.clear();
		for (int i = 0; i < shelves.length; i++) {
			shelves[i] = 0;
		}
		listener.onUpdate();
		return new Job(0, CNT_SHELVES-1);
	}

	public void setListener(UpdateListener listener) {
		this.listener = listener;
	}

	public int getShelf(int index) {
		return shelves[index];
	}

	public Collection<Worker> getWorkers() {
		return Collections.unmodifiableCollection(workers.values());
	}
	
	public Collection<Job> getJobs() {
		return Collections.unmodifiableCollection(jobs);
	}
	
	public Job[] split(Job job) {
		int color = job.color;
		int from = job.from;
		int to = job.to;

        int size = to - from + 1;

        int mid = size > (CNT_SHELVES / 4) ? from + CNT_SHELVES / 4 - 1 : (from + to) / 2;

		Worker worker = getWorker();
		worker.goTo(mid + 0.5);

		Job[] newJobs = new Job[] { new Job(color, from, mid), new Job(color, mid+1, to) };
		jobs.remove(job);
		jobs.addAll(Arrays.asList(newJobs));
		listener.onUpdate();
		sleep(150);
		return newJobs;
	}

	public void claim(Job job) {
		sleep(0);
		Worker worker = getWorker();
		jobs.remove(job);
		job.color = worker.color;
		jobs.add(job);
		listener.onUpdate();
	}

	public void paint(Job job) {
		claim(job);
		Worker worker = getWorker();
		for (int index = job.from; index <= job.to; index++) {
			worker.goTo(index);
			
			while (shelves[index] < HEIGHT) {
				shelves[index]++;
				listener.onUpdate();
				sleep(1);
			}
		}
	}

	private void sleep(int millis) {
		try {
			synchronized (waiting) {
				while (waiting.get()) {
					waiting.wait();
				}
			}
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// empty
		}
	}

	private Worker getWorker() {
		int hashCode = Thread.currentThread().hashCode();
		Worker worker = workers.get(hashCode);
		if (worker == null) {
			worker = new Worker(hashCode);
			workers.put(hashCode, worker);
		}
		return worker;
	}

}
