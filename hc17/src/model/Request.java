package model;

public class Request {
	public int id;
	public int video_id;
	public int endpoint_id;
	public int requests;
	
	public Request(int id, int video_id, int endpoint_id, int requests) {
		this.id = id;
		this.video_id = video_id;
		this.endpoint_id = endpoint_id;
		this.requests = requests;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		return buf.append("{").append(video_id).append(",").append(requests).append("}").toString();
	}
}
