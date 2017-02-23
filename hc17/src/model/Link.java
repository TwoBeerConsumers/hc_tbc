package model;

public class Link {
	public int id;
	public int latency;
	
	public Link(int id, int latency) {
		this.id = id;
		this.latency = latency;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		return buf.append("{").append(id).append(",").append(latency).append("}").toString();
	}
}
