package _05forkjoinpool;

import java.awt.*;
import java.util.Collection;

import javax.swing.JComponent;

public class WallPainterCanvas extends JComponent implements UpdateListener {
	
	private WallPainterModel model;
	
	public WallPainterCanvas(WallPainterModel model) {
		this.model = model;
		model.setListener(this);
		setMinimumSize(new Dimension(10 * WallPainterModel.CNT_SHELVES, 100));
	}
	
	@Override
	public void onUpdate() {
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Dimension size = getSize();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, size.width, size.height);
		
		int shelfWidth = size.width / WallPainterModel.CNT_SHELVES;
		int shelfHeight = shelfWidth * 10;

		for (int i = 0; i < WallPainterModel.CNT_SHELVES; i++) {
			int x = i * shelfWidth;
			int y = 0;
			
			int shelf = model.getShelf(i);
			int height = shelf  * shelfHeight/ WallPainterModel.HEIGHT;
			
			g.setColor(Color.BLUE);
			g.fillRect(x, y, shelfWidth, height);
			
			g.setColor(Color.BLACK);
			g.drawRect(x, y, shelfWidth, shelfHeight);
		}
		
		Collection<Job> jobs = model.getJobs();
		Collection<WallPainterModel.Worker> workers = model.getWorkers();
		for (Job job : jobs) {
			int x = job.getFrom() * shelfWidth + shelfWidth / 4;
			int y = shelfHeight + shelfWidth / 4;
			int width = (job.getTo() - job.getFrom()) * shelfWidth + shelfWidth / 2;
			int height = shelfWidth / 2;
			g.setColor(new Color(job.getColor()));
			g.fillRect(x, y, width, height);
		}

		for (WallPainterModel.Worker worker : workers) {
			int x = (int) (worker.getPos() * shelfWidth);
			int y = shelfHeight + shelfWidth;
			g.setColor(new Color(worker.getColor()));
			g.fillArc(x, y, shelfWidth, shelfWidth, 0, 360);
		}
	}

}
