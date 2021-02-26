import java.util.Comparator;

public class Node implements Comparable {
	public String string;
	public Long value;

	public Node(String string, Long value) {
		this.string = string;
		this.value = value;
	}

	@Override
	public int compareTo(Object o) {
		Node node = (Node)o;
		if(this.value.compareTo(node.value)==0){
			return string.compareTo(node.string)*-1;
		}else {
			return this.value.compareTo(node.value);
		}
	}
}
