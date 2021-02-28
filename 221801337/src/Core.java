import java.util.List;

public class Core {
	private WordReader wr;
	private TopK topK = new TopK();

	public Core() {

	}

	public Core(WordReader wr) {
		this.wr = wr;
	}

	public void start() {
		String s;

		while ((s = wr.nextWord()) != null) {
			topK.add(s);
		}
	}

	public String next() {
		String s = wr.nextWord();
		return s;
	}

	public List<Node> getTopK(Long k) {
		return topK.topK(k);
	}

	public WordReader getWordReader() {
		return wr;
	}

	public void setWordReader(WordReader wr) {
		this.wr = wr;
	}

	public long getCharNumber() {
		return wr.getCharNumber();
	}

	public long getWordNumber() {
		return wr.getWordNumber();
	}

	public long getLineNumber() {
		return wr.getLineNumber();
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append("characters: " + getCharNumber() + "\n");
		res.append("words: " + getWordNumber() + "\n");
		res.append("lines: " + getLineNumber() + "\n");

		List<Node> topK = getTopK(10L);
		for (Node node : topK) {
			res.append(node.string + ": " + node.value + "\n");
		}
		return res.toString();
	}

	public void clear() {
		if (wr != null)
			wr.clear();
		topK.clear();
	}
}
