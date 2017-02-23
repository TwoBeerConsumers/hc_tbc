package model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Video {
	public int size;
	public PriorityQueue<Request> requests = new PriorityQueue<Request>(new Comparator<Request>() {
		@Override
		public int compare(Request o1, Request o2) {
			return (o1.requests > o2.requests) ? 1:((o1.requests == o2.requests) ? 0:-1);
		}
	});
	
	public Video(int size) {
		this.size = size;
	}

	public void addRequest(Request request) {
		requests.add(request);
	}
}
