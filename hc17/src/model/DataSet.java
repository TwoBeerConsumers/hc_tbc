package model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DataSet {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static int V; // videos < 10000
	public static int E; // endpoints < 1000
	public static int R; // request descriptors < 1000000
	public static int C; // cache servers < 1000
	public static int X; // capacity in MB < 500000
	public static int total_mb = 0;
	public static int request_mb = 0;
	
	public static Video[] videos;
	public static Endpoint[] endpoints;
	public static Request[] requests;
	public static Cache[] caches;
	public static PriorityQueue<Benefit> benefits = new PriorityQueue<Benefit>(new Comparator<Benefit>() {
		@Override
		public int compare(Benefit o1, Benefit o2) {
			return o1.savings > o2.savings ? -1:(o1.savings < o2.savings ? 1:0);
		}
	});
	
	
	public static void load(String filename) throws IOException {
		Path path = Paths.get(filename);
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			// Read first line
			V = scanner.nextInt();
			E = scanner.nextInt();
			R = scanner.nextInt();
			C = scanner.nextInt();
			X = scanner.nextInt();
			
			scanner.nextLine();
			System.out.println("Server Capacity: " + X + "MB");
			System.out.println("Videos: " + V);
			System.out.println("Endpoints: " + E);
			System.out.println("Caches: " + C);
			System.out.println("Requests: " + R);
			
			
			// Set up Caches
			caches = new Cache[C];
			for(int i=0;i<C;i++) {
				caches[i] = new Cache();
			}
			System.out.println("Read Caches");
			
			// Read the video size in MB
			videos = new Video[V];
			int index = 0;
			for (int i=0; i<V; i++) {
				videos[index++] = new Video(scanner.nextInt());
				total_mb += videos[index-1].size;
			}
			scanner.nextLine();
			System.out.println("Read Videos");

			// Process the endpoints
			endpoints = new Endpoint[E];
			for (int i=0; i<E; i++) {
				// Process each endpoint
				endpoints[i] = new Endpoint(scanner.nextInt());
				int links_size = scanner.nextInt();
				Link[] links = new Link[links_size];
				scanner.nextLine();
				for (int j=0;j<links_size; j++) {
					links[j] = new Link(scanner.nextInt(), scanner.nextInt());
					scanner.nextLine();
				}
				endpoints[i].links = links;
			}
			System.out.println("Read Endpoints");

			// Process the requests
			requests = new Request[R];
			for (int i=0; i<R; i++) {
				requests[i] = new Request(i, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
				endpoints[requests[i].endpoint_id].addRequest(requests[i]);
				videos[requests[i].video_id].addRequest(requests[i]);
				
				request_mb += videos[requests[i].video_id].size;
				scanner.nextLine();
			}
			System.out.println("Read Requests");
			
			System.out.println("Total Videos: " + total_mb);
			System.out.println("Requested Videos: " + request_mb);
			scanner.close();
		}
	}
}
