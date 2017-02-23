package hc17;

import java.io.IOException;
import java.util.HashSet;

import model.Benefit;
import model.DataSet;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
//		DataSet.load("me_at_the_zoo.in");
//		DataSet.load("videos_worth_spreading.in");
		DataSet.load("trending_today.in");
//		DataSet.load("kittens.in");
		
		int[] mask = new int[DataSet.R];
		int[] cache = new int[DataSet.C];
		HashSet<Integer>[] videos = (HashSet<Integer>[]) new HashSet[DataSet.C];
		
		while (!DataSet.benefits.isEmpty()) {
			Benefit benefit = DataSet.benefits.poll();
			if (mask[benefit.request.id] == 0) {
				HashSet<Integer> set = videos[benefit.cache_id];
				if (set == null) {
					set = videos[benefit.cache_id] = new HashSet<Integer>();
				}
				
				if (set.contains(benefit.request.video_id)) {
					mask[benefit.request.id] = 1;
				} else {
					int bft = cache[benefit.cache_id] + DataSet.videos[benefit.request.video_id].size;
					if (bft <= DataSet.X) {
						set.add(benefit.request.video_id);
						cache[benefit.cache_id] = bft;
						mask[benefit.request.id] = 1;
					}
				}
			}
		}
		
		int total_mb = 0;
		for (int i=0; i<cache.length; i++) {
			total_mb += cache[i];
			System.out.println(i+". " + cache[i] + " MB: " + videos[i]);
		}
		System.out.println(DataSet.request_mb + " == " + total_mb+"\n\n-----------------------------------------");
		System.out.println(cache.length);
		for (int i=0; i<cache.length; i++) {
			System.out.print(i);
			for (int v : videos[i]){
				System.out.print(" "+v);
			}
			System.out.println();
		}
		
	}

}
