package model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Endpoint {
	public int latency;
	public Link[] links;
	
	public PriorityQueue<Request> requests = new PriorityQueue<Request>(new Comparator<Request>() {
		@Override
		public int compare(Request o1, Request o2) {
			return o1.requests > o2.requests ? -1:(o1.requests < o2.requests ? 1:0);
		}
	});
	
	public Endpoint(int latency) {
		this.latency = latency;
	}

	public void addRequest(Request request) {
		requests.add(request);
		for (Link link : links) {
			DataSet.benefits.add(new Benefit(request, link.id, request.requests*(latency-link.latency)));
		}
	}
}
