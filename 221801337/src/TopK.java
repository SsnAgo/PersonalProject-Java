import java.util.*;

public class TopK {
	private HashMap<String, Long> map = new HashMap<>();
	private PriorityQueue<Node> pq = new PriorityQueue<>();
	public boolean add(String word){
		if(map.containsKey(word)){
			map.put(word,map.get(word)+1);
		}else{
			map.put(word,1L);
		}
		return true;
	}
	public Long get(String word){
		return map.get(word);
	}
	public List<Node> topK(Long k){
		for(Map.Entry<String,Long> entry: map.entrySet()){
			pq.add(new Node(entry.getKey(),entry.getValue()));
			if(pq.size()>k)pq.poll();
		}
		List<Node> list = new ArrayList<>();
		while(!pq.isEmpty()){
			list.add(pq.poll());
		}
		Collections.reverse(list);
		return list;
	}
	public void clear(){
		map.clear();
		pq.clear();
	}
}
