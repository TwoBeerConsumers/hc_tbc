package model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Cache {
	public PriorityQueue<Request> requests = new PriorityQueue<Request>(new Comparator<Request>() {
		@Override
		public int compare(Request o1, Request o2) {
			return o1.requests > o2.requests ? -1:(o1.requests < o2.requests ? 1:0);
		}
	});
	
	public void addRequest(Request request) {
		requests.add(request);
		
	}
}
