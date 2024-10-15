package _05forkjoinpool;

public class Job {
	
	int color;
	
	final int from;
	
	final int to;
	
	public Job(int from, int to) {
		this(0xffffff, from, to);
	}
	
	public Job(int color, int from, int to) {
		super();
		this.color = color;
		this.from = from;
		this.to = to;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	@Override
	public String toString() {
		return "Job [color=" + color + ", from=" + from + ", to=" + to + "]";
	}
	
}