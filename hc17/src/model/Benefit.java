package model;

public class Benefit {
	public Request request;
	public int savings;
	public int cache_id;
	
	public Benefit(Request request, int cache_id, int savings) {
		this.request = request;
		this.cache_id = cache_id;
		this.savings = savings;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		return buf.append("[ ").append(request.toString()).append(", ").append(cache_id).append(", ").append(savings).append("]").toString();
	}
}
