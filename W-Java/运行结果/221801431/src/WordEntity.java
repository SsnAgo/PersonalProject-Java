
public class WordEntity implements Comparable<WordEntity>{
		
	private String word;
	private Integer count;

	public WordEntity(String key, Integer value) {
		super();
	    this.word = key;
	    this.count = value;
	}
	public String getKey() {
		return word;
	}
	public Integer getValue() {
	    return count;
	}
	public void setKey(String key) {
		this.word = key;
	}
	public void setValue(Integer value) {
		this.count = value;
	}

	/**
	 * 从写比较函数
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(WordEntity o) {
	    int cmp = count.intValue()-o.count.intValue();
	    return (cmp == 0 ? word.compareTo(o.getKey()):-cmp);
	}

}

