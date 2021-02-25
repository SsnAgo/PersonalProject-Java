import javafx.util.Pair;

import java.util.*;

public class TrieTree {
	public boolean add(String s){
		return true;
	}
	public boolean remove(String s){
		return true;
	}
	public int query(String s){
		return 0;
	}
	public Pair<String,Integer>[] topK(int k){
		PriorityQueue<Pair<String, Integer>> pq = getMyPriorityQueue();
		Pair<String,Integer> pairs[] = new Pair[k];

		return pairs;
	}

	private PriorityQueue<Pair<String,Integer>> getMyPriorityQueue(){
		PriorityQueue<Pair<String,Integer>> pq = new PriorityQueue<>(
				(o1,o2)->{
					if(o1.getValue()==o2.getValue()){
						if(o1.getKey()==o2.getKey()){
							return 0;
						}else {
							return o1.getKey().compareTo(o2.getKey());
						}
					}else{
						return o1.getValue().compareTo(o2.getValue());
					}
				}
		);
		return pq;
	}
}
