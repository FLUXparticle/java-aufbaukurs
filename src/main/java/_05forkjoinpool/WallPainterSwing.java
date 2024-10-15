package _05forkjoinpool;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import javax.swing.*;

import static _05forkjoinpool.WallPainterModel.*;

public class WallPainterSwing extends JFrame {

    private final WallPainterModel model = new WallPainterModel();

    public WallPainterSwing() {
		setTitle("Wall Pinter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		WallPainterCanvas canvas = new WallPainterCanvas(model);
		add(canvas, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		{
			JPanel panelInner = new JPanel();
			
			JButton buttonSingle = new JButton("Single Thread");
			buttonSingle.addActionListener(this::startSingleThread);
			panelInner.add(buttonSingle);
			
			JButton buttonSinglePool = new JButton("Single Thread Executor");
			buttonSinglePool.addActionListener(this::startSingleThreadExecutor);
			panelInner.add(buttonSinglePool);
			
			JButton buttonFixedPool = new JButton("Fixed Thread Pool");
			buttonFixedPool.addActionListener(this::startFixedThreadPool);
			panelInner.add(buttonFixedPool);
			
			JButton buttonCachedPool = new JButton("Cached Thread Pool");
			buttonCachedPool.addActionListener(this::startCachedThreadPool);
			panelInner.add(buttonCachedPool);
			
			panel.add(panelInner);
		}
		
		{
			JPanel panelInner = new JPanel();

            JButton buttonSingleForkJoinPool = new JButton("Single Fork Join Pool");
            buttonSingleForkJoinPool.addActionListener(this::startSingleForkJoinPool);
            panelInner.add(buttonSingleForkJoinPool);

            JButton buttonForkJoinPool = new JButton("Fork Join Pool");
            buttonForkJoinPool.addActionListener(this::startForkJoinPool);
            panelInner.add(buttonForkJoinPool);

            JButton buttonParallelStream = new JButton("ParallelStream");
            buttonParallelStream.addActionListener(this::startParallelStream);
            panelInner.add(buttonParallelStream);

            JButton buttonPauseResume = new JButton("Pause/Resume");
			buttonPauseResume.addActionListener(event -> model.pauseResume());
			panelInner.add(buttonPauseResume);
			
			panel.add(panelInner);
		}
		
		add(panel, BorderLayout.SOUTH);
	}

    private void startSingleThread(ActionEvent event) {
        Job job = model.newJob();
        SingleThread thread = new SingleThread(model, job);
        thread.start();
    }

    private void startSingleThreadExecutor(ActionEvent event) {
        Job job = model.newJob();
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = job.from; i <= job.to; i += BATCH_SIZE) {
            ThreadPoolWorker runnable = new ThreadPoolWorker(model, new Job(i, i + BATCH_SIZE-1));
            service.execute(runnable);
        }
    }

    private void startFixedThreadPool(ActionEvent event) {
        Job job = model.newJob();
        int parallelism = 4;
        ExecutorService service = Executors.newFixedThreadPool(parallelism);
        for (int i = job.from; i <= job.to; i += BATCH_SIZE) {
            ThreadPoolWorker runnable = new ThreadPoolWorker(model, new Job(i, i + BATCH_SIZE-1));
            service.execute(runnable);
        }
    }

    private void startCachedThreadPool(ActionEvent event) {
        Job job = model.newJob();
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            for (int i = job.from; i <= job.to; i++) {
                ThreadPoolWorker runnable = new ThreadPoolWorker(model, new Job(i, i));
                service.execute(runnable);
                Thread.sleep(150);
            }
            return null;
        });
    }

    private void startSingleForkJoinPool(ActionEvent event) {
        Job job = model.newJob();
        int parallelism = 1;
        ForkJoinPool pool = new ForkJoinPool(parallelism);
        ForkJoinWorker task = new ForkJoinWorker(model, job);
        pool.execute(task);
    }

    private void startForkJoinPool(ActionEvent event) {
        Job job = model.newJob();
        int parallelism = 4;
        ForkJoinPool pool = new ForkJoinPool(parallelism);
        ForkJoinWorker task = new ForkJoinWorker(model, job);
        pool.execute(task);
    }

    private void startParallelStream(ActionEvent event) {
        Job job = model.newJob();

        new Thread(() -> {
            IntStream.rangeClosed(job.from, job.to).parallel()
                    .mapToObj(i -> new Job(i, i))
                    .forEach(model::paint);
        }).start();
    }

    public static void main(String[] args) {
		new WallPainterSwing().setVisible(true);
	}

}
